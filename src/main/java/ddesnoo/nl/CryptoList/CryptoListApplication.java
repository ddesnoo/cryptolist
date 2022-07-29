package ddesnoo.nl.CryptoList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptoListApplication {


	public static void main(String[] args) {
		SpringApplication.run(CryptoListApplication.class, args);

		/**
		 * This function is called when the program boots-up. This function will generate a Logfile to show what happens to the API en what a client uses it for.
		 */
		Logging.LogFile();
	}

}
