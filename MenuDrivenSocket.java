import java.io.*;
import java.net.*;

public class MenuDrivenSocket {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("1. Send Messages");
            System.out.println("2. Receive Messages");

            System.out.print("Enter your choice: ");
            int userChoice = Integer.parseInt(reader.readLine());

            if (userChoice == 1) {
                // Send Messages
                System.out.print("Enter the IP Address to connect: ");
                String ipAddress = reader.readLine();

                System.out.print("Enter the Port Number to connect: ");
                int portNumber = Integer.parseInt(reader.readLine());

                Socket socket = new Socket(ipAddress, portNumber);

                sendMessage(socket);
            } else if (userChoice == 2) {
                // Receive Messages
                System.out.print("Enter the Port Number to listen: ");
                int portNumber = Integer.parseInt(reader.readLine());

                ServerSocket serverSocket = new ServerSocket(portNumber);
                System.out.println("Waiting for incoming connection on port " + portNumber + "...");
                Socket socket = serverSocket.accept();
                System.out.println("Connection established.");

                receiveMessage(socket);
            } else {
                System.out.println("Invalid choice. Exiting...");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(Socket socket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

        while (true) {
            System.out.print("Enter your message (type 'exit' to end): ");
            String message = input.readLine();

            if ("exit".equalsIgnoreCase(message)) {
                break;
            }

            output.println(message);
        }

        socket.close();
    }

    private static void receiveMessage(Socket socket) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (true) {
            String receivedMessage = input.readLine();

            if (receivedMessage == null) {
                break;
            }

            System.out.println("Received: " + receivedMessage);
        }

        socket.close();
    }
}

