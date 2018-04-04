package com.github.YusukeHasegawa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import ai.api.model.AIResponse;

@Profile("!test")
@SpringBootApplication
public class DialogflowSampleApplication implements CommandLineRunner {

	private static final String INPUT_PROMPT = "> ";
	private static final int ERROR_EXIT_CODE = 1;

	private DialogflowService dialogflowService;

	public DialogflowSampleApplication(DialogflowService dialogflowService) {
		super();
		this.dialogflowService = dialogflowService;
	}

	@Override
	public void run(String... args) throws Exception {

		String line;

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			System.out.print(INPUT_PROMPT);
			while (null != (line = reader.readLine())) {
				try {

					AIResponse response = dialogflowService.request(line);
					System.out.println(response.getResult().getFulfillment().getSpeech());

				} catch (Exception ex) {
					ex.printStackTrace();
				}

				System.out.print(INPUT_PROMPT);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		System.out.println("See ya!");

	}

	public static void main(String[] args) {
		if (args.length < 1) {
			showHelp("Please specify access token", ERROR_EXIT_CODE);
		}
		SpringApplication.run(DialogflowSampleApplication.class, args);
	}

	private static void showHelp(String errorMessage, int exitCode) {
		if (errorMessage != null && errorMessage.length() > 0) {
			System.err.println(errorMessage);
			System.err.println();
		}

		System.out.println("Usage: access token");
		System.out.println();
		System.out.println("APIKEY  Your unique application key");
		System.out.println("        See https://docs.api.ai/docs/key-concepts for details");
		System.out.println();
		System.exit(exitCode);
	}

}
