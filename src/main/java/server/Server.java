package server;

import json.Json;
import protocol.request.RequestOperations;
import protocol.response.LogoutResponse;
import protocol.response.Response;
import server.exceptions.ServerResponseException;
import server.procedure.*;
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
                    .addRoute(RequestOperations.LOGIN, new LoginProcedure())
                    .addRoute(RequestOperations.LOGOUT, new LogoutProcedure())
                    .addRoute(RequestOperations.ADMIN_BUSCAR_USUARIOS, new AdminFindUsersProcedure())
                    .addRoute(RequestOperations.ADMIN_BUSCAR_USUARIO, new AdminFindUserProcedure())
                    .addRoute(RequestOperations.ADMIN_CADASTRAR_USUARIO, new AdminCreateUserProcedure())
                    .addRoute(RequestOperations.ADMIN_ATUALIZAR_USUARIO, new AdminUpdateUserProcedure())
                    .addRoute(RequestOperations.ADMIN_DELETAR_USUARIO, new AdminDeleteUserProcedure())
                    .build();
        }
        start();
    }

    public static void main(String[] args) throws IOException
    {
        InetAddress ipAddress = InetAddress.getByName("0.0.0.0");
        final int port = 24801;
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
                System.out.println("Request Recebido: " + inputLine);
                Response<?> response;

                try {
                    response = routes.serve(inputLine);
                } catch (ServerResponseException e) {
                    response = e.intoResponse();
                }

                String jsonResponse = Json.toJson(response);
                System.out.println("Response Enviado: " + jsonResponse);
                out.println(jsonResponse);

                if (response instanceof LogoutResponse)
                    break;
            }
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            System.exit(1);
        }

        assert (clientSocket.isClosed());
    }
}