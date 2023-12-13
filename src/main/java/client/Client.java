package client;

import client.interfaces.*;
import client.interfaces.request.*;
import client.interfaces.response.*;
import com.google.gson.JsonSyntaxException;
import json.Json;
import json.validation.ConstraintViolated;
import json.validation.ValidateJson;
import protocol.request.*;
import protocol.request.routes.*;
import protocol.response.*;
import protocol.response.routes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        InitInterface init = new InitInterface(null);
        String IPServer = init.getIp();
        int port = Integer.parseInt(init.getPorta());

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

                if(response instanceof LoginResponse) {
                    LoginInterfaceResponse loginInterfaceResponse = new LoginInterfaceResponse(null);
                    token = ((LoginResponse) response).payload().getToken();
                    System.out.println("token was set");
                }
                if(response instanceof LogoutResponse) {
                    LogoutInterfaceResponse logoutInterfaceResponse = new LogoutInterfaceResponse(null, jsonResponse);
                    token = null;
                    break;
                }
                if(response instanceof CreateUserResponse) {
                    CreateUserInterfaceResponse createUserResponse = new CreateUserInterfaceResponse(null);
                }
                if(response instanceof FindUserResponse) {
                    FindUserInterfaceResponse findUserResponse = new FindUserInterfaceResponse(null, jsonResponse);
                }
                if(response instanceof FindUsersResponse) {
                    FindUsersInterfaceResponse findUsersInterfaceResponse = new FindUsersInterfaceResponse(null, jsonResponse);
                }
                if(response instanceof UpdateUserResponse) {
                    UpdateUserInterfaceResponse updateUserInterfaceRequest = new UpdateUserInterfaceResponse(null);
                }
                if(response instanceof DeleteUserResponse) {
                    DeleteUserInterfaceResponse deleteUserInterfaceResponse = new DeleteUserInterfaceResponse(null, jsonResponse);
                }
                if(response instanceof CreateNodeResponse) {
                    CreateNodeInterfaceResponse createNodeInterfaceResponse = new CreateNodeInterfaceResponse(null);
                }
                if(response instanceof FindNodesResponse) {
                    FindNodesInterfaceResponse findNodesResponse = new FindNodesInterfaceResponse(null, jsonResponse);
                }
                if(response instanceof UpdateNodeResponse) {
                    UpdateNodeInterfaceResponse updateNodeInterfaceResponse = new UpdateNodeInterfaceResponse(null);
                }
                if(response instanceof DeleteNodeResponse) {
                    DeleteNodeInterfaceResponse deleteNodeInterfaceResponse = new DeleteNodeInterfaceResponse(null);
                }
                if(response instanceof CreateSegmentResponse) {
                    CreateSegmentInterfaceResponse createSegmentInterfaceResponse = new CreateSegmentInterfaceResponse(null);
                }
                if(response instanceof FindSegmentsResponse) {
                    FindSegmentsInterfaceResponse findSegmentsResponse = new FindSegmentsInterfaceResponse(null, jsonResponse);
                }
                if(response instanceof UpdateSegmentResponse) {
                    UpdateSegmentInterfaceResponse updateSegmentInterfaceResponse = new UpdateSegmentInterfaceResponse(null);
                }
                if(response instanceof DeleteSegmentResponse) {
                    DeleteSegmentInterfaceResponse deleteSegmentInterfaceResponse = new DeleteSegmentInterfaceResponse(null);
                }
                if(response instanceof FindRouteResponse) {
                    FindRouteInterfaceResponse findRouteInterfaceResponse = new FindRouteInterfaceResponse(null, jsonResponse);
                }

                if(response instanceof ErrorResponse) {
                    ErrorInterfaceResponse errorInterfaceResponse = new ErrorInterfaceResponse(null, jsonResponse);
                }

                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("ERRO: não foi possível ler o stdIn");
        }
    }

    private static Request<?> requestFactory(BufferedReader stdin, String token) throws IOException {
        while (true) {
            Home op = new Home(null);
            String operation = op.getOperation();

            if (operation == null) {
                throw new IOException();
            }

            switch (operation) {
                case RequestOperations.LOGIN:
                    LoginInterfaceRequest loginInterface = new LoginInterfaceRequest(null);
                    return new LoginRequest(loginInterface.getEmail(), loginInterface.getSenha());
                case RequestOperations.LOGOUT:
                    return new LogoutRequest(token);
                case RequestOperations.ADMIN_BUSCAR_USUARIOS:
                    return new AdminFindUsersRequest(token);
                case RequestOperations.ADMIN_BUSCAR_USUARIO:
                    AdminFindUserInterfaceRequest adminFindUserInterface = new AdminFindUserInterfaceRequest(null);
                    return new AdminFindUserRequest(token, adminFindUserInterface.getRegistro());
                case RequestOperations.ADMIN_CADASTRAR_USUARIO:
                    AdminCreateUserInterfaceRequest adminCreateUserInterface = new AdminCreateUserInterfaceRequest(null);
                    return new AdminCreateUserRequest(token, adminCreateUserInterface.getNome(),
                            adminCreateUserInterface.getEmail(), adminCreateUserInterface.getSenha());
                case RequestOperations.ADMIN_ATUALIZAR_USUARIO:
                    AdminUpdateUserInterfaceRequest adminUpdateUserInterface = new AdminUpdateUserInterfaceRequest(null);
                    return new AdminUpdateUserRequest(token, adminUpdateUserInterface.getRegistro(),
                            adminUpdateUserInterface.getNome(), adminUpdateUserInterface.getEmail(),
                            adminUpdateUserInterface.getSenha(), adminUpdateUserInterface.getTipo());
                case RequestOperations.ADMIN_DELETAR_USUARIO:
                    AdminDeleteUserInterfaceRequest adminDeleteUserInterface = new AdminDeleteUserInterfaceRequest(null);
                    return new AdminDeleteUserRequest(token, adminDeleteUserInterface.getRegistro());
                case RequestOperations.BUSCAR_USUARIO:
                    return new FindUserRequest(token);
                case RequestOperations.CADASTRAR_USUARIO:
                    CreateUserInterfaceRequest createUserInterface = new CreateUserInterfaceRequest(null);
                    return new CreateUserRequest(token, createUserInterface.getNome(),
                            createUserInterface.getEmail(), createUserInterface.getSenha());
                case RequestOperations.ATUALIZAR_USUARIO:
                    UpdateUserInterfaceRequest updateUserInterface = new UpdateUserInterfaceRequest(null);
                    return new UpdateUserRequest(token, updateUserInterface.getEmail(),
                            updateUserInterface.getNome(), updateUserInterface.getSenha());
                case RequestOperations.DELETAR_USUARIO:
                    DeleteUserInterfaceRequest deleteUserInterface = new DeleteUserInterfaceRequest(null);
                    return new DeleteUserRequest(token, deleteUserInterface.getEmail(), deleteUserInterface.getSenha());
                case RequestOperations.CADASTRAR_PDI:
                    AdminCreateNodeInterfaceRequest createNodeInterface = new AdminCreateNodeInterfaceRequest(null);
                    return new AdminCreateNodeRequest(token, createNodeInterface.getNome(), createNodeInterface.getCoordenadaX(),
                            createNodeInterface.getCoordenadaY(), createNodeInterface.getAviso(), createNodeInterface.getAcessivel());
                case RequestOperations.ATUALIZAR_PDI:
                    AdminUpdateNodeInterfaceRequest updateNodeInterface = new AdminUpdateNodeInterfaceRequest(null);
                    return new AdminUpdateNodeRequest(token, updateNodeInterface.getId(), updateNodeInterface.getNome(),
                            updateNodeInterface.getAviso(), updateNodeInterface.getAcessivel());
                case RequestOperations.BUSCAR_PDIS:
                    return new AdminFindNodesRequest(token);
                case RequestOperations.DELETAR_PDI:
                    AdminDeleteNodeInterfaceRequest deleteNodeInterface = new AdminDeleteNodeInterfaceRequest(null);
                    return new AdminDeleteNodeRequest(token, deleteNodeInterface.getId());
                case RequestOperations.CADASTRAR_SEGMENTO:
                    AdminCreateSegmentInterfaceRequest createSegmentInterface = new AdminCreateSegmentInterfaceRequest(null);
                    return new AdminCreateSegmentRequest(token, createSegmentInterface.getPdiInicial(),
                            createSegmentInterface.getPdiFinal(), createSegmentInterface.getAviso(),
                            createSegmentInterface.getAcessivel());
                case RequestOperations.ATUALIZAR_SEGMENTO:
                    AdminUpdateSegmentInterfaceRequest updateSegmentInterface = new AdminUpdateSegmentInterfaceRequest(null);
                    return new AdminUpdateSegmentRequest(token, updateSegmentInterface.getPdiInicial(), updateSegmentInterface.getPdiFinal(),
                            updateSegmentInterface.getAviso(), updateSegmentInterface.getAcessivel());
                case RequestOperations.BUSCAR_SEGMENTOS:
                    return new AdminFindSegmentsRequest(token);
                case RequestOperations.DELETAR_SEGMENTO:
                    AdminDeleteSegmentInterfaceRequest deleteSegmentInterface = new AdminDeleteSegmentInterfaceRequest(null);
                    return new AdminDeleteSegmentRequest(token, deleteSegmentInterface.getPdiInicial(), deleteSegmentInterface.getPdiFinal());
                case RequestOperations.BUSCAR_ROTA:
                    FindRouteInterfaceRequest findRouteInterface = new FindRouteInterfaceRequest(null);
                    return new FindRouteRequest(token, findRouteInterface.getPdiInicial(), findRouteInterface.getPdiFinal());
                default:
                    System.out.println("Operação inválida. Tente novamente.");
            }
        }
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
            if(requestClass == AdminCreateNodeRequest.class) {
                response = Json.fromJson(json, CreateNodeResponse.class);
            }
            if(requestClass == AdminUpdateNodeRequest.class) {
                response = Json.fromJson(json, UpdateNodeResponse.class);
            }
            if(requestClass == AdminFindNodesRequest.class) {
                response = Json.fromJson(json, FindNodesResponse.class);
            }
            if(requestClass == AdminDeleteNodeRequest.class) {
                response = Json.fromJson(json, DeleteNodeResponse.class);
            }
            if(requestClass == AdminCreateSegmentRequest.class) {
                response = Json.fromJson(json, CreateSegmentResponse.class);
            }
            if(requestClass == AdminUpdateSegmentRequest.class) {
                response = Json.fromJson(json, UpdateSegmentResponse.class);
            }
            if(requestClass == AdminFindSegmentsRequest.class) {
                response = Json.fromJson(json, FindSegmentsResponse.class);
            }
            if(requestClass == AdminDeleteSegmentRequest.class) {
                response = Json.fromJson(json, DeleteSegmentResponse.class);
            }
            if(requestClass == FindRouteRequest.class) {
                response = Json.fromJson(json, FindRouteResponse.class);
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

