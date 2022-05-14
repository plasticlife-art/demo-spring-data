package com.haulmont.spring.data.demo;

import com.haulmont.spring.data.demo.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	private static final Logger log = LoggerFactory.getLogger(DemoApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private ForesterRepository foresterRepository;
	private ReserveRepository reserveRepository;

	@Bean
	public CommandLineRunner demo(ForesterRepository foresterRepository, ReserveRepository reserveRepository) {
		return (args) -> {
			this.foresterRepository = foresterRepository;
			this.reserveRepository = reserveRepository;

			var reserveSamara = initReserveSamara(reserveRepository);

			initForesters(reserveSamara);

			printForesters();

			testForesterRepository();

			log.info("Foresters found with findByReserve(reserveSamara):");
			log.info("--------------------------------");
			foresterRepository.findByReserve(reserveSamara).forEach(forester -> {
				log.info(forester.toString());
			});
			log.info("");

			log.info("Reserve found with findByName(\"L1\"):");
			log.info("--------------------------------");
			reserveRepository.findByName("L1").forEach(reserve -> {
				log.info(reserve.toString());
			});
			log.info("");

		};
	}

	private Reserve initReserveSamara(ReserveRepository reserveRepository) {
		return reserveRepository.save(new Reserve("L1", new Address("Russia", "Samara")));
	}

	private void testForesterRepository() {
		Forester customer = foresterRepository.findById(1L).get();
		log.info("Forester found with findById(1L):");
		log.info("--------------------------------");
		log.info(customer.toString());
		log.info("");

		log.info("Forester found with findByLastNameIgnoreCase('Bauer'):");
		log.info("--------------------------------------------");
		foresterRepository.findByLastNameIgnoreCase("Bauer").forEach(bauer -> {
			log.info(bauer.toString());
		});
		log.info("");
	}

	private void printForesters() {
		log.info("Foresters found with findAll():");
		log.info("-------------------------------");
		for (Forester customer : foresterRepository.findAll()) {
			log.info(customer.toString());
		}
		log.info("");
	}

	private void initForesters(Reserve reserve) {
		var foresterJack = new Forester("Jack", "Bauer");
		foresterJack.setReserve(reserve);
		foresterRepository.save(foresterJack);

		var foresterKim = new Forester("Kim", "Bauer");
		foresterKim.setReserve(reserve);
		foresterRepository.save(foresterKim);


		var foresterChloe = new Forester("Chloe", "O'Brian");
		foresterChloe.setReserve(reserve);
		foresterRepository.save(foresterChloe);

		foresterRepository.save(new Forester("David", "Palmer"));
		foresterRepository.save(new Forester("Michelle", "Dessler"));
	}


}
