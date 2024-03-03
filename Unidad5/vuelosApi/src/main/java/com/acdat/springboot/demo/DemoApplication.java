package com.acdat.springboot.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
/*	public static void GetRequest(){
		HttpURLConnection conn = null;
		try{
			URL url = new URL("http://localhost:8080/api-rest/empleados");
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Accept","application/json");

			if (conn.getResponseCode() == 200){
				Scanner scn = new Scanner(conn.getInputStream());
				String response = scn.useDelimiter("\\Z").next();
				scn.close();

				JSONArray jsonArray = new JSONArray(response);
				for(int i =0;i<jsonArray.length();i++){
					JSONObject jsonObject = (JSONObject) jsonArray.get(i);
					System.out.println(jsonObject.get("depno")+" "+jsonObject.get("nombre"));
				}
			}else {
				System.out.println("Fallo  en la conexion");
			}
			conn.disconnect();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void PostRequest() {
		try {
			URL url = new URL("http://localhost:8080/api=rest/empleados");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			String jsonInputString = new JSONObject().put("empno",1234)
					.put("nombre","Diez")
					.put("puesto","Dependiente")
					.put("departamento",new JSONObject().put("depno",10).put("nombre","Marketing").put("ubicacion","Barcelona").toString()).toString();

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_CREATED) {
				System.out.println("POST correcto");
			} else {
				System.out.println("POST erroneo con codigo: " + responseCode);
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void deleteRequest() {
		try {
			URL url = new URL("http://localhost:8080/api-rest/empleados/7369"); // Cambiar id por el que se desea eliminar
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");

			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
				System.out.println("DELETE satisfactorio");
			} else {
				System.out.println("DELETE fallido con codigo: " + responseCode);
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}

