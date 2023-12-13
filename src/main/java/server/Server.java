package server;

import calculus.euclidean.EuclideanDistance;
import json.Json;
import protocol.request.RequestOperations;
import protocol.response.LoginResponse;
import protocol.response.LogoutResponse;
import protocol.response.Response;
import server.controller.NodeController;
import server.controller.SegmentController;
import server.datatransferobject.node.CreateNode;
import server.datatransferobject.segment.CreateSegment;
import server.datatransferobject.user.CreateUser;
import server.exceptions.ServerResponseException;
import server.controller.UserController;
import server.interfaces.Home;
import server.interfaces.InitInterface;
import server.interfaces.UserList;
import server.processes.*;
import server.processes.routes.*;
import server.router.Router;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server extends Thread {
    private final Socket clientSocket;
    private Router routes = null;
    private static final Home home = new Home();

    private Server(Socket clientSoc) {
        clientSocket = clientSoc;
        if (routes == null) {
            routes = Router.builder()
                    .addRoute(RequestOperations.LOGIN, new LoginProcess())
                    .addRoute(RequestOperations.LOGOUT, new LogoutProcess())
                    .addRoute(RequestOperations.ADMIN_BUSCAR_USUARIO, new AdminFindUserProcess())
                    .addRoute(RequestOperations.ADMIN_BUSCAR_USUARIOS, new AdminFindUsersProcess())
                    .addRoute(RequestOperations.ADMIN_CADASTRAR_USUARIO, new AdminCreateUserProcess())
                    .addRoute(RequestOperations.ADMIN_ATUALIZAR_USUARIO, new AdminUpdateUserProcess())
                    .addRoute(RequestOperations.ADMIN_DELETAR_USUARIO, new AdminDeleteUserProcess())
                    .addRoute(RequestOperations.BUSCAR_USUARIO, new FindUserProcess())
                    .addRoute(RequestOperations.CADASTRAR_USUARIO, new CreateUserProcess())
                    .addRoute(RequestOperations.ATUALIZAR_USUARIO, new UpdateUserProcess())
                    .addRoute(RequestOperations.DELETAR_USUARIO, new DeleteUserProcess())
                    .addRoute(RequestOperations.BUSCAR_PDIS, new FindNodesProcess())
                    .addRoute(RequestOperations.CADASTRAR_PDI, new CreateNodeProcess())
                    .addRoute(RequestOperations.ATUALIZAR_PDI, new UpdateNodeProcess())
                    .addRoute(RequestOperations.DELETAR_PDI, new DeleteNodeProcess())
                    .addRoute(RequestOperations.BUSCAR_SEGMENTOS, new FindSegmentsProcess())
                    .addRoute(RequestOperations.CADASTRAR_SEGMENTO, new CreateSegmentProcess())
                    .addRoute(RequestOperations.ATUALIZAR_SEGMENTO, new UpdateSegmentProcess())
                    .addRoute(RequestOperations.DELETAR_SEGMENTO, new DeleteSegmentProcess())
                    .addRoute(RequestOperations.BUSCAR_ROTA, new FindRouteProcess())
                    .build();
        }
        start();
    }

    public static void main(String[] args) throws IOException, ServerResponseException {
        UserController.getInstance();
        NodeController.getInstance();
        SegmentController.getInstance();

        UserController.getInstance().createUser(new CreateUser("admin@email.com", "123456", "Admin", true));

        createMap();

        InitInterface init = new InitInterface(null);
        final int port = Integer.parseInt(init.getPorta());

        InetAddress ipAddress = InetAddress.getByName("0.0.0.0");
        try(ServerSocket serverSocket = new ServerSocket(port, 0, ipAddress)) {
            System.out.println("Connection Socket Created");
            home.setVisible(true);
            home.setPortaField(port);
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
                    UserList.getInstance().removeUserInList(home, String.valueOf(clientSocket.getInetAddress()));
                    break;
                }
                if (response instanceof LogoutResponse) {
                    UserList.getInstance().removeUserInList(home, String.valueOf(clientSocket.getInetAddress()));
                    break;
                }

                if(response instanceof LoginResponse) {
                    UserList.getInstance().addUserInList(home, String.valueOf(clientSocket.getInetAddress()));
                }
            }
        } catch (IOException e) {
            System.err.println("Problem with Communication Server");
            //System.exit(1);
        }
        assert (clientSocket.isClosed());
    }

    public static void createMap() throws ServerResponseException {
        NodeController.getInstance().createNode(new CreateNode("Portaria", 0.0, 0.0, "", true)); //1
        NodeController.getInstance().createNode(new CreateNode("Escada Portaria-Capela", 0.0, 100.0, "", true)); //2
        NodeController.getInstance().createNode(new CreateNode("Capela", 0.0, 105.0, "", true)); //3
        NodeController.getInstance().createNode(new CreateNode("Auditorio", -20.0, 105.0, "", true)); //4
        NodeController.getInstance().createNode(new CreateNode("Escada Inicio 1.o andar", 20.0, 105.0, "", true)); //5
        NodeController.getInstance().createNode(new CreateNode("Escada Baixo-Meio 1.o andar", 20.0, 115.0, "", true)); //6
        NodeController.getInstance().createNode(new CreateNode("Lab. 6", 22.0, 105.0, "", true)); //7
        NodeController.getInstance().createNode(new CreateNode("Lab. 7", 22.0, 125.0, "", true)); //8
        NodeController.getInstance().createNode(new CreateNode("Lab. 8", 22.0, 135.0, "", true)); //9
        NodeController.getInstance().createNode(new CreateNode("Escada Bebedouro", 22.0, 140.0, "", true)); //10
        NodeController.getInstance().createNode(new CreateNode("LaCa", 20.0, 140.0, "", true)); //11
        NodeController.getInstance().createNode(new CreateNode("Rampa", -20.0, 140.0, "", true)); //12
        NodeController.getInstance().createNode(new CreateNode("Escada Inicio Audiotorio 1.o andar", -20.0, 103.0, "", true)); //13
        NodeController.getInstance().createNode(new CreateNode("Escada Meio Audiotorio 1.o andar", -20.0, 93.0, "", true)); //14
        NodeController.getInstance().createNode(new CreateNode("Escada Cima Audiotorio 1.o andar", -20.0, 105.0, "", true)); //15
        NodeController.getInstance().createNode(new CreateNode("Lab. 4", 10.0, 105.0, "", true)); //16
        NodeController.getInstance().createNode(new CreateNode("Lab. 2", 15.0, 105.0, "", true)); //17
        NodeController.getInstance().createNode(new CreateNode("Escada Cima 1.o andar", 18.0, 105.0, "", true)); //18
        NodeController.getInstance().createNode(new CreateNode("Escada Cima-Meio 1.o andar", 18.0, 115.0, "", true)); //19
        NodeController.getInstance().createNode(new CreateNode("Lab. 1", 20.0, 105.0, "", true)); //20
        NodeController.getInstance().createNode(new CreateNode("Lab. 3", 22.0, 105.0, "", true)); //21
        NodeController.getInstance().createNode(new CreateNode("Lab. 5", 22.0, 115.0, "", true)); //22
        NodeController.getInstance().createNode(new CreateNode("DAINF", 22.0, 130.0, "", true)); //23

        SegmentController.getInstance().createSegment(new CreateSegment((long) 1, (long) 2, EuclideanDistance.calculateDistance(0.0, 0.0, 0.0, 100.0), "", true)); //1
        SegmentController.getInstance().createSegment(new CreateSegment((long) 2, (long) 3, EuclideanDistance.calculateDistance(0.0, 100.0, 0.0, 105.0), "Cuidado ao subir a escada", true));//2
        SegmentController.getInstance().createSegment(new CreateSegment((long) 3, (long) 4, EuclideanDistance.calculateDistance(0.0, 105.0, -20.0, 105.0), "", true));//3
        SegmentController.getInstance().createSegment(new CreateSegment((long) 3, (long) 5, EuclideanDistance.calculateDistance(0.0, 105.0, 20.0, 105.0), "", true));//4
        SegmentController.getInstance().createSegment(new CreateSegment((long) 5, (long) 6, EuclideanDistance.calculateDistance(20.0, 105.0, 20.0, 115.0), "Cuidado ao subir a escada", true));//5
        SegmentController.getInstance().createSegment(new CreateSegment((long) 5, (long) 7, EuclideanDistance.calculateDistance(20.0, 105.0, 22.0, 105.0), "", true));//6
        SegmentController.getInstance().createSegment(new CreateSegment((long) 7, (long) 8, EuclideanDistance.calculateDistance(22.0, 105.0, 22.0, 125.0), "", true));//7
        SegmentController.getInstance().createSegment(new CreateSegment((long) 8, (long) 9, EuclideanDistance.calculateDistance(22.0, 125.0, 22.0, 135.0), "", true));//8
        SegmentController.getInstance().createSegment(new CreateSegment((long) 9, (long) 10, EuclideanDistance.calculateDistance(22.0, 135.0, 22.0, 140.0), "", true));//9
        SegmentController.getInstance().createSegment(new CreateSegment((long) 10, (long) 11, EuclideanDistance.calculateDistance(22.0, 140.0, 20.0, 140.0), "Cuidado ao subir a escada", true));//10
        SegmentController.getInstance().createSegment(new CreateSegment((long) 11, (long) 12, EuclideanDistance.calculateDistance(20.0, 140.0, -20.0, 140.0), "", true));//11
        SegmentController.getInstance().createSegment(new CreateSegment((long) 12, (long) 4, EuclideanDistance.calculateDistance(-20.0, 140.0, -20.0, 105.0), "", true));//12
        SegmentController.getInstance().createSegment(new CreateSegment((long) 4, (long) 13, EuclideanDistance.calculateDistance(-20.0, 105.0, -20.0, 103.0), "", true));//13
        SegmentController.getInstance().createSegment(new CreateSegment((long) 13, (long) 14, EuclideanDistance.calculateDistance(-20.0, 103.0, -20.0, 93.0), "Cuidado ao subir a escada", true));//14
        SegmentController.getInstance().createSegment(new CreateSegment((long) 14, (long) 15, EuclideanDistance.calculateDistance(-20.0, 93.0, -20.0, 105.0), "Cuidado ao subir a escada", true));//15
        SegmentController.getInstance().createSegment(new CreateSegment((long) 15, (long) 16, EuclideanDistance.calculateDistance(-20.0, 105.0, 10.0, 105.0), "", true));//16
        SegmentController.getInstance().createSegment(new CreateSegment((long) 16, (long) 17, EuclideanDistance.calculateDistance(10.0, 105.0, 15.0, 105.0), "", true));//17
        SegmentController.getInstance().createSegment(new CreateSegment((long) 17, (long) 18, EuclideanDistance.calculateDistance(15.0, 105.0, 18.0, 105.0), "", true));//18
        SegmentController.getInstance().createSegment(new CreateSegment((long) 18, (long) 19, EuclideanDistance.calculateDistance(18.0, 105.0, 18.0, 95.0), "", true));//19
        SegmentController.getInstance().createSegment(new CreateSegment((long) 19, (long) 6, EuclideanDistance.calculateDistance(18.0, 115.0, 20.0, 115.0), "Cuidado ao descer a escada", true));//20
        SegmentController.getInstance().createSegment(new CreateSegment((long) 18, (long) 20, EuclideanDistance.calculateDistance(18.0, 105.0, 20.0, 105.0), "", true));//21
        SegmentController.getInstance().createSegment(new CreateSegment((long) 20, (long) 21, EuclideanDistance.calculateDistance(20.0, 105.0, 22.0, 105.0), "", true));//22
        SegmentController.getInstance().createSegment(new CreateSegment((long) 21, (long) 22, EuclideanDistance.calculateDistance(22.0, 105.0, 22.0, 115.0), "", true));//23
        SegmentController.getInstance().createSegment(new CreateSegment((long) 22, (long) 23, EuclideanDistance.calculateDistance(22.0, 115.0, 22.0, 130.0), "", true));//24

        SegmentController.getInstance().createSegment(new CreateSegment((long) 2, (long) 1, EuclideanDistance.calculateDistance(0.0, 0.0, 0.0, 100.0), "", true)); //1
        SegmentController.getInstance().createSegment(new CreateSegment((long) 3, (long) 2, EuclideanDistance.calculateDistance(0.0, 100.0, 0.0, 105.0), "Cuidado ao descer a escada", true));//2
        SegmentController.getInstance().createSegment(new CreateSegment((long) 4, (long) 3, EuclideanDistance.calculateDistance(0.0, 105.0, -20.0, 105.0), "", true));//3
        SegmentController.getInstance().createSegment(new CreateSegment((long) 5, (long) 3, EuclideanDistance.calculateDistance(0.0, 105.0, 20.0, 105.0), "", true));//4
        SegmentController.getInstance().createSegment(new CreateSegment((long) 6, (long) 5, EuclideanDistance.calculateDistance(20.0, 105.0, 20.0, 115.0), "Cuidado ao descer a escada", true));//5
        SegmentController.getInstance().createSegment(new CreateSegment((long) 7, (long) 5, EuclideanDistance.calculateDistance(20.0, 105.0, 22.0, 105.0), "", true));//6
        SegmentController.getInstance().createSegment(new CreateSegment((long) 8, (long) 7, EuclideanDistance.calculateDistance(22.0, 105.0, 22.0, 125.0), "", true));//7
        SegmentController.getInstance().createSegment(new CreateSegment((long) 9, (long) 8, EuclideanDistance.calculateDistance(22.0, 125.0, 22.0, 135.0), "", true));//8
        SegmentController.getInstance().createSegment(new CreateSegment((long) 10, (long) 9, EuclideanDistance.calculateDistance(22.0, 135.0, 22.0, 140.0), "", true));//9
        SegmentController.getInstance().createSegment(new CreateSegment((long) 11, (long) 10, EuclideanDistance.calculateDistance(22.0, 140.0, 20.0, 140.0), "Cuidado ao descer a escada", true));//10
        SegmentController.getInstance().createSegment(new CreateSegment((long) 12, (long) 11, EuclideanDistance.calculateDistance(20.0, 140.0, -20.0, 140.0), "", true));//11
        SegmentController.getInstance().createSegment(new CreateSegment((long) 4, (long) 12, EuclideanDistance.calculateDistance(-20.0, 140.0, -20.0, 105.0), "", true));//12
        SegmentController.getInstance().createSegment(new CreateSegment((long) 13, (long) 4, EuclideanDistance.calculateDistance(-20.0, 105.0, -20.0, 103.0), "", true));//13
        SegmentController.getInstance().createSegment(new CreateSegment((long) 14, (long) 13, EuclideanDistance.calculateDistance(-20.0, 103.0, -20.0, 93.0), "Cuidado ao descer a escada", true));//14
        SegmentController.getInstance().createSegment(new CreateSegment((long) 15, (long) 14, EuclideanDistance.calculateDistance(-20.0, 93.0, -20.0, 105.0), "Cuidado ao descer a escada", true));//15
        SegmentController.getInstance().createSegment(new CreateSegment((long) 16, (long) 15, EuclideanDistance.calculateDistance(-20.0, 105.0, 10.0, 105.0), "", true));//16
        SegmentController.getInstance().createSegment(new CreateSegment((long) 17, (long) 16, EuclideanDistance.calculateDistance(10.0, 105.0, 15.0, 105.0), "", true));//17
        SegmentController.getInstance().createSegment(new CreateSegment((long) 18, (long) 17, EuclideanDistance.calculateDistance(15.0, 105.0, 18.0, 105.0), "", true));//18
        SegmentController.getInstance().createSegment(new CreateSegment((long) 19, (long) 18, EuclideanDistance.calculateDistance(18.0, 105.0, 18.0, 95.0), "", true));//19
        SegmentController.getInstance().createSegment(new CreateSegment((long) 6, (long) 19, EuclideanDistance.calculateDistance(18.0, 115.0, 20.0, 115.0), "Cuidado ao subir a escada", true));//20
        SegmentController.getInstance().createSegment(new CreateSegment((long) 20, (long) 18, EuclideanDistance.calculateDistance(18.0, 105.0, 20.0, 105.0), "", true));//21
        SegmentController.getInstance().createSegment(new CreateSegment((long) 21, (long) 20, EuclideanDistance.calculateDistance(20.0, 105.0, 22.0, 105.0), "", true));//22
        SegmentController.getInstance().createSegment(new CreateSegment((long) 22, (long) 21, EuclideanDistance.calculateDistance(22.0, 105.0, 22.0, 115.0), "", true));//23
        SegmentController.getInstance().createSegment(new CreateSegment((long) 23, (long) 22, EuclideanDistance.calculateDistance(22.0, 115.0, 22.0, 130.0), "", true));//24
    }
}