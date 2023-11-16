package client;

import com.google.gson.JsonSyntaxException;
import json.Json;
import json.validation.ConstraintViolated;
import json.validation.ValidateJson;
import protocol.Optional;
import protocol.request.*;
import protocol.request.header.Header;
import protocol.response.*;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
//        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("IP: ");
//        String IPServer = stdIn.readLine();
//        System.out.print("Port: ");
//        int port = Integer.parseInt(stdIn.readLine());

        ClientInterface intr = new ClientInterface(null);
        String IPServer = intr.getIp();
        int port = Integer.parseInt(intr.getPorta());

        try (Socket echoSocket = new Socket(IPServer, port);
             PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                     echoSocket.getInputStream()));
             BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in))
        ) {
            communicateWithServer(out, in, stdin);
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + IPServer);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to: " + IPServer);
            System.exit(1);
        }
    }

    private static void communicateWithServer(PrintWriter out, BufferedReader in, BufferedReader stdin) {
        String token = null;
        try {
            while (true) {
                Request<?> request = requestFactory(stdin, token);
                String jsonRequest = Json.toJson(request);
                out.println(jsonRequest);
                System.out.println();
                System.out.println("Request criado: " + request);
                System.out.println("Request enviado: " + jsonRequest);
                System.out.println();

                String jsonResponse = in.readLine();
                if (jsonResponse == null) {
                    System.err.println("ERRO: JSON Response nulo.");
                    break;
                }

                System.out.println("Response recebido: " + jsonResponse);
                Response<?> response = handleResponse(jsonResponse, request);
                System.out.println("Response criado: " + response);

                if (response == null) {
                    continue;
                }

                if (response instanceof LoginResponse) {
                    token = ((LoginResponse) response).payload().getToken();
                    System.out.println("token was set");
                }

                if (response instanceof LogoutResponse) {
                    token = null;
                    break;
                }

                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("ERRO: não foi possível ler o stdIn");
        }
    }

    private static Request<?> requestFactory(BufferedReader stdin, String token) throws IOException {
        while (true) {
//            System.out.print("Insira a operação: ");
//            String operation = stdin.readLine();
            Operation op = new Operation(null);
            String operation = op.getText();

            if (operation == null) {
                throw new IOException();
            }

            switch (operation) {
                case RequestOperations.LOGIN:
                    return makeRequest(stdin, token, LoginRequest.class);
                case RequestOperations.LOGOUT:
                    return makeRequest(stdin, token, LogoutRequest.class);
                case RequestOperations.ADMIN_BUSCAR_USUARIOS:
                    return makeRequest(stdin, token, AdminFindUsersRequest.class);
                case RequestOperations.ADMIN_BUSCAR_USUARIO:
                    return makeRequest(stdin, token, AdminFindUserRequest.class);
                case RequestOperations.ADMIN_CADASTRAR_USUARIO:
                    return makeRequest(stdin, token, AdminCreateUserRequest.class);
                case RequestOperations.ADMIN_ATUALIZAR_USUARIO:
                    return makeRequest(stdin, token, AdminUpdateUserRequest.class);
                case RequestOperations.ADMIN_DELETAR_USUARIO:
                    return makeRequest(stdin, token, AdminDeleteUserRequest.class);
                case RequestOperations.BUSCAR_USUARIO:
                    return makeRequest(stdin, token, FindUserRequest.class);
                case RequestOperations.CADASTRAR_USUARIO:
                    return makeRequest(stdin, token, CreateUserRequest.class);
                case RequestOperations.ATUALIZAR_USUARIO:
                    return makeRequest(stdin, token, UpdateUserRequest.class);
                case RequestOperations.DELETAR_USUARIO:
                    return makeRequest(stdin, token, DeleteUserRequest.class);
                default:
                    System.out.println("Operação inválida. Tente novamente.");
            }
        }
    }

    private static <T> T makeRequest(BufferedReader stdin, String token, Class<T> requestClass) throws IOException {
        for (Constructor<?> constructor : requestClass.getConstructors()) {
            Parameter[] parameters = constructor.getParameters();
            boolean shouldSkip = false;

            for (Parameter parameter : parameters) {
                if (parameter.getType() == Header.class) {
                    shouldSkip = true;
                    break;
                }
            }
            if (shouldSkip) {
                continue;
            }

            Object[] constructorArguments = new Object[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i].getName().toLowerCase().contains("token")) {
                    constructorArguments[i] = token;
                    continue;
                }
                System.out.print(parameters[i].getName());
                if (parameters[i].isAnnotationPresent(Optional.class)) {
                    System.out.print(" (opcional)");
                }
                System.out.print(": ");
                String line = stdin.readLine();
                if (line.isBlank() || line.isEmpty()) {
                    constructorArguments[i] = null;
                } else if (parameters[i].getType() == Long.class) {
                    constructorArguments[i] = Long.parseLong(line);
                } else if (parameters[i].getType() == Integer.class) {
                    constructorArguments[i] = Integer.parseInt(line);
                } else if (parameters[i].getType() == Boolean.class) {
                    constructorArguments[i] = Boolean.parseBoolean(line);
                } else {
                    constructorArguments[i] = line;
                }
            }
            try {

                return (T) constructor.newInstance(constructorArguments);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("ERRO: Não foi possível criar a instância: " + requestClass.getName());
    }

    private static Response<?> handleResponse(String json, Request<?> request) {
        Response<?> response = null;
        try {
            try {
                ErrorResponse errorResponse = Json.fromJson(json, ErrorResponse.class);
                if(errorResponse.payload() != null) {
                    return errorResponse;
                }
            } catch (JsonSyntaxException ignored) { }

            Class<?> requestClass = request.getClass();
            if (requestClass == LoginRequest.class) {
                response = Json.fromJson(json, LoginResponse.class);
            }
            if (requestClass == LogoutRequest.class) {
                response = Json.fromJson(json, LogoutResponse.class);
            }
            if (requestClass == AdminFindUsersRequest.class) {
                response = Json.fromJson(json, FindUsersResponse.class);
            }
            if (requestClass == AdminFindUserRequest.class) {
                response = Json.fromJson(json, FindUserResponse.class);
            }
            if (requestClass == AdminCreateUserRequest.class) {
                response = Json.fromJson(json, CreateUserResponse.class);
            }
            if (requestClass == AdminUpdateUserRequest.class) {
                response = Json.fromJson(json, UpdateUserResponse.class);
            }
            if (requestClass == AdminDeleteUserRequest.class) {
                response = Json.fromJson(json, DeleteUserResponse.class);
            }
            if (requestClass == FindUserRequest.class) {
                response = Json.fromJson(json, FindUserResponse.class);
            }
            if (requestClass == CreateUserRequest.class) {
                response = Json.fromJson(json, CreateUserResponse.class);
            }
            if (requestClass == UpdateUserRequest.class) {
                response = Json.fromJson(json, UpdateUserResponse.class);
            }
            if (requestClass == DeleteUserRequest.class) {
                response = Json.fromJson(json, DeleteUserResponse.class);
            }
            ValidateJson.validate(response);
            return response;
        } catch (ConstraintViolated e) {
            System.err.println("Não foi possível validar a resposta\n" + e.getMessage());
            return response;
        } catch (JsonSyntaxException e) {
            System.err.println("Erro no json recebido");
        }
        return null;
    }
}

