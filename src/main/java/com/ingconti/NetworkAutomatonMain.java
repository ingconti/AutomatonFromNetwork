package com.ingconti;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 to test against console:

  /usr/bin/nc 127.0.0.1 1234
 and type in console: server will receive.
*/



public class NetworkAutomatonMain {

    static final int portNumber = 1234;
    private static Automaton automaton;


    static Boolean readLoop(BufferedReader in, PrintWriter out ){
        // waits for data and reads it in until connection dies
        // readLine() blocks until the server receives a new line from client
        String s = "";

        // NOTE: this code SHOULD go in Model, we put here only for simplicity!
        String stateString = "";
        Boolean goOn = false;
        try {
            while ((s = in.readLine()) != null) {
                s = s.toUpperCase();
                System.out.println(s);
                if (s.equals("G")){
                    goOn = automaton.evolve();
                }else if (s.equals("P")){
                    automaton.setPaid();
                }else{
                    DinnerPhase ph = DinnerPhase.fromString(s);
                    goOn = automaton.evolveTo(ph);
                }

                stateString = automaton.getState().toString();

                stateString = goOn? (" new state: " + stateString) : " NOT MOVED FROM " + stateString;
                System.out.println(stateString);
                out.println(stateString);

            }// while

            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    public static void main( String[] args ) {

        automaton = new Automaton();

        System.out.println("Server started!");

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            readLoop(in, out);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Server done!");

    }


}

