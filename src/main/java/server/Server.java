package server;

import json.Json;
import protocol.request.RequestOperations;
import protocol.response.LogoutResponse;
import protocol.response.Response;
import server.datatransferobject.CreateUser;
import server.exceptions.ServerResponseException;
import server.controller.UserController;
import server.processes.*;
import server.router.Router;

import java.net.*;
import java.io.*;

public class Server extends Thread {
    private final Socket clientSocket;
    private Router routes = null;

    private Server(Socket clientSoc) {
        clientSocket = clientSoc;
        if (routes == null) {
            routes = Router.builder()
                    .addRoute(RequestOperations.LOGIN, new LoginProcess())
                    .addRoute(RequestOperations.LOGOUT, new LogoutProcess())
                    .addRoute(RequestOperations.ADMIN_BUSCAR_USUARIOS, new AdminFindUsersProcess())
                    .addRoute(RequestOperations.ADMIN_BUSCAR_USUARIO, new AdminFindUserProcess())
                    .addRoute(RequestOperations.ADMIN_CADASTRAR_USUARIO, new AdminCreateUserProcess())
                    .addRoute(RequestOperations.ADMIN_ATUALIZAR_USUARIO, new AdminUpdateUserProcess())
                    .addRoute(RequestOperations.ADMIN_DELETAR_USUARIO, new AdminDeleteUserProcess())
                    .addRoute(RequestOperations.CADASTRAR_USUARIO, new CreateUserProcess())
                    .build();
        }
        start();
    }

    public static void main(String[] args) throws IOException, ServerResponseException {
        UserController.getInstance()
                .createUser(new CreateUser("felipe@email.com", "123456", "Felipe", true));

        UserController.getInstance()
                .createUser(new CreateUser("nome@email.com", "123456", "Nome", false));

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Port: ");
        final int port = Integer.parseInt(stdIn.readLine());

        InetAddress ipAddress = InetAddress.getByName("0.0.0.0");
        try(ServerSocket serverSocket = new ServerSocket(port, 0, ipAddress)) {
            System.out.println("Connection Socket Created");
            while (true) {
                try {
                    System.out.println("Waiting for Connection");
                    new Server(serverSocket.accept());
                } catch (IOException e) {
                    System.err.println("Accept failed.");
                    System.exit(1);
                }
            }
        } catch (IOException e) {
            System.err.printf("Could not listen on port: %d.%n", port);
            System.exit(1);
        }
    }

    public void run() {
        System.out.println("New Communication Thread Started");

        try (clientSocket;
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(clientSocket.getInetAddress() + " - Request Recebido: " + inputLine);
                Response<?> response;
                try {
                    response = routes.serve(inputLine);
                } catch (ServerResponseException e) {
                    response = e.intoResponse();
                }
                String jsonResponse = Json.toJson(response);
                System.out.println(clientSocket.getInetAddress() + " - Response Enviado: " + jsonResponse);
                out.println(jsonResponse);
                if(!clientSocket.isConnected() || clientSocket.isClosed()) {
                    break;
                }
                if (response instanceof LogoutResponse)
                    break;
            }
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            //System.exit(1);
        }
        assert (clientSocket.isClosed());
    }
}