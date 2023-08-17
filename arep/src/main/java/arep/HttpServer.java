package arep;

import java.net.*;
import java.io.*;
public class HttpServer {
public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = null;
    try {
        serverSocket = new ServerSocket(35000);
    } catch (IOException e) {
        System.err.println("Could not listen on port: 35000.");
        System.exit(1);
    }
    boolean running = true ;
    while(running){
        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
        new InputStreamReader(
        clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received: " + inputLine);
            if(firstLine){
                firstLine = false;
                path = inputLine.split(" ")[1];
            }
            if (!in.ready()) {
                break;
            }
        }

        System.out.println("Path" + path);

        if(path.startsWith("/hello")){
            outputLine = getHello(path);
        }else{
            outputLine = getDefautIndex();
        }

        outputLine = getDefautIndex;
        out.println(outputLine);

        out.close();
        in.close();
        clientSocket.close();
    }
    serverSocket.close();
    }

    public static String getHello(String path){
        String name = path.split("=")[1];
        String response  = "HTTP/1.1 200 OK \r\n"
        ;
    }

    public static String getDefautIndex(){
        String response; /*=
                "<!DOCTYPE html>"
                +"<html>"
                +    "<head>"
                 +       "<title>Form Example</title>"
                  +     " <meta charset="UTF-8">"
                   +    " <meta name="viewport" content="width=device-width, initial-scale=1.0">"
                    +"</head>"
                    +"<body>"
+                       " <h1>Form with GET</h1>"
 +                      " <form action="/hello">"
  +                         " <label for="name">Name:</label><br>"
   +                        " <input type="text" id="name" name="name" value="John"><br><br>"
    +                       " <input type="button" value="Submit" onclick="loadGetMsg()">"
     +                  " </form> "
      +                 " <div id="getrespmsg"></div>"
       +         
        +                "<script>"
         +                   "function loadGetMsg() {"
          +                     " let nameVar = document.getElementById("name").value;"
           +                     "const xhttp = new XMLHttpRequest();"
            +                    "xhttp.onload = function() {"
             +                       "document.getElementById("getrespmsg").innerHTML ="
              +                     " this.responseText;"
                                "}"
               +               "  xhttp.open("GET", "/hello?name="+nameVar);"
                +                "xhttp.send();"
                 +           "}"
                  +      "</script>"
                
                   +     "<h1>Form with POST</h1>"
                    +    "<form action="/hellopost">"
                     +       "<label for="postname">Name:</label><br>"
                      +      "<input type="text" id="postname" name="name" value="John"><br><br>"
                       +    " <input type="button" value="Submit" onclick="loadPostMsg(postname)">"
                        +"</form>"
                        
        +               " <div id="postrespmsg"></div>"
                        
         +               "<script>"
         +                   "function loadPostMsg(name){"
          +                      "let url = "/hellopost?name=" + name.value;"
                
           +                     "fetch (url, {method: 'POST'})"
            +                        ".then(x => x.text())"
             +                       ".then(y => document.getElementById("postrespmsg").innerHTML = y);"
              +             " }"
                +       " </script>"
               +     "</body>"
            +    "</html>"
             +       "}";
           */      
        return response;
    }

}
