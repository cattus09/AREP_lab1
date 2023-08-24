package arep;

import java.net.*;
import java.util.HashMap;
import java.io.*;
import org.json.*;;

public class HttpServer {
    /**
     * Metodo para iniciar el programa
     * @param args main
     * @throws IOException exception
    */
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
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine, outputLine, title = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if(inputLine.contains("info?title=")){
                    String[] prov = inputLine.split("title=");
                    title = (prov[1].split("HTTP")[0]).replace(" ", "");
                }
                if (!in.ready()) {
                    break;
                }
            }

            if(!title.equals("")){
                String response = HttpConnection.requestTitle(title, "http://www.omdbapi.com/?t="+title+"&apikey=7ca9f0c2");
                outputLine ="HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + "<br>"
                        + "<table border=\" 1 \"> \n " 
                        + doTable(response) 
                        +"    </table>";
            }else {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/html\r\n"
                        + "\r\n"
                        + getDefautIndex();
            }
            
            out.println(outputLine);
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    
    /**
     * Formar el contenido de una tabla basado en un String que se le pase con el formato de un archivo JSON
     * @param response Archivo en formato JSON a transformar en tabla
     * @return String del contenido de una tabla formada en HTML
     */
    public static String doTable(String response){
        HashMap<String, String> hash = new HashMap<String, String>();
        JSONArray arr = new JSONArray(response);
        for (int i = 0; i < arr.length(); i++) {
            JSONObject object = arr.getJSONObject(i);
            for (String key : object.keySet()) {
                hash.put(key.toString(), object.get(key).toString());
            }
        }
        String dataTable = "<tr> \n";
        for (String key : hash.keySet()) {
            String value = hash.get(key);
            dataTable += "<tr> \n"
                    + "<td>" + key + "</td> \n"
                    + "<td>" + value + "</td> \n"
                    + "</tr> \n";
        }
        return dataTable;
    }
    /**
     * @return html de la pagina a usar 
     */
    public static String getDefautIndex(){
        String response =   "<!DOCTYPE html>\n" +
                            "<html>\n" +
                            "<head>\n" +
                            "    <title>Buscador de peliculas</title>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "<h1>Buscar una pelicula</h1>\n" +
                            "<form action=\"/hello\">\n" +
                            "    <label for=\"name\">Titulo de la pelicula a buscar:</label><br>\n" +
                            "    <input type=\"text\" id=\"name\" name=\"name\" value=\"The Avengers\"><br><br>\n" +
                            "    <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n" +
                            "</form>\n" +
                            "<div id=\"getrespmsg\"></div>\n" +
                            "\n" +
                            "<script>\n" +
                            "            function loadGetMsg() {\n" +
                            "                let nameVar = document.getElementById(\"name\").value;\n" +
                            "                const xhttp = new XMLHttpRequest();\n" +
                            "                xhttp.onload = function() {\n" +
                            "                    document.getElementById(\"getrespmsg\").innerHTML =\n" +
                            "                    this.responseText;\n" +
                            "                }\n" +
                            "                xhttp.open(\"GET\", \"/info?title=\"+nameVar);\n" +
                            "                xhttp.send();\n" +
                            "            }\n" +
                            "        </script>\n" +
                            "</body>\n" +
                            "</html>";
        return response;
    }

}
