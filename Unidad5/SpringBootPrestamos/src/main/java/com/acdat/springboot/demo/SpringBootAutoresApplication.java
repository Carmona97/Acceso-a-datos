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
public class SpringBootAutoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAutoresApplication.class, args);
	}
}
