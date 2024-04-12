package fr.hetic;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

import fr.hetic.model.Adresse;
import fr.hetic.model.Animal;
import fr.hetic.model.Civilites;
import fr.hetic.model.EtreHumain;
import fr.hetic.model.Personne;
import org.apache.commons.lang3.*;

@SuppressWarnings(value = {"rawtypes", "unchecked"})
public class Main {
	
	static List personnes = new ArrayList();
	private static List animaux = new ArrayList();
	private static List etresHumains = new ArrayList();

	static void initPopulation() {
		Animal divine = new Animal("Divine", "Européen", LocalDate.of(2009, Month.JULY, 7));
		animaux.add(divine);
		Animal haskell = new Animal("Haskell", "Européen", LocalDate.of(2013, Month.SEPTEMBER, 1));
		animaux.add(haskell);
		Animal milou = new Animal("Milou", "Chartreux", LocalDate.of(2019, Month.NOVEMBER, 2));
		animaux.add(milou);
		Animal filou = new Animal("Filou", "Teckel", LocalDate.of(2019, Month.OCTOBER, 24));
		animaux.add(filou);
		Animal rex = new Animal("Rex", "Caniche", LocalDate.of(2016, Month.SEPTEMBER, 21));
		animaux.add(rex);
		Animal animalEric = new Animal("Eric", "Caniche", LocalDate.of(2013, Month.JANUARY, 21));
		animaux.add(animalEric);
		
		Personne eric = new Personne(Civilites.MONSIEUR, "Eric", "Siber", LocalDate.of(1981, Month.AUGUST, 29));
		eric.setAdresse(new Adresse("105", "avenue Général De Gaulle", 78500, "Sartrouville"));
		eric.getAnimaux().add(divine);
		eric.getAnimaux().add(haskell);
		personnes.add(eric);
		
		Personne timo = new Personne(Civilites.MONSIEUR, "Timo", "Siber", LocalDate.of(2016, Month.OCTOBER, 8));
		timo.setAdresse(new Adresse("105", "avenue Général De Gaulle", 78500, "Sartrouville"));
		timo.getAnimaux().add(milou);
		personnes.add(timo);
		
		Personne jean = new Personne("Monsieur", "Jean", "Dupond", LocalDate.of(1978, Month.OCTOBER, 29));
		jean.setAdresse(new Adresse("12", "avenue de la République", 75001, "Paris"));
		personnes.add(jean);
		
		Personne marie = new Personne(Civilites.MADAME, "Marie", "Dupond", LocalDate.of(1980, Month.SEPTEMBER, 10));
		marie.setAdresse(new Adresse("105", "avenue de la République", 75001, "Paris"));
		marie.getAnimaux().add(rex);
		personnes.add(marie);
		
		etresHumains.addAll(animaux);
		etresHumains.addAll(personnes);
	}

	public static List trierParPrenom(List elements) {
		List copy = new ArrayList(elements);
		Collections.sort(copy, (EtreHumain e1, EtreHumain e2) -> e1.getPrenom().compareTo(e2.getPrenom()));
		return copy;
	}

	public static long compter(List elements) {
		return elements.stream().count();
	}

	public static long compter(List<Personne> elements, String nom) {
		return elements.stream()
				.filter(personne -> nom.equals(personne.getNom()))
				.count();
	}

	public static List<String> listerPrenoms(List<EtreHumain> elements) {
		return elements.stream()
				.map(EtreHumain::getPrenom)
				.distinct()
				.sorted()
				.collect(Collectors.toList());
	}

	public static List<String> listerVilles(List<Personne> elements) {
		return elements.stream()
				.filter(personne -> personne.getAdresse() != null)
				.map(personne -> personne.getAdresse().getVille())
				.collect(Collectors.toList());
	}

	public static List<String> listerRaces(List<Personne> elements) {
		return elements.stream()
				.flatMap(personne -> personne.getAnimaux().stream())
				.map(Animal::getRace)
				.collect(Collectors.toList());
	}

	public static Map<String, Long> compterHabitantsParVille(List<Personne> elements) {
		return elements.stream()
				.collect(Collectors.groupingBy(personne -> {
					if (personne.getAdresse() != null) {
						return personne.getAdresse().getVille();
					} else {
						return "No Address";
					}
				}, Collectors.counting()));
	}
	
	public static void main(String[] args) {
		initPopulation();
		System.out.println(personnes);
		
		// step 1 : utiliser une enum
		System.out.println(Civilites.MONSIEUR);
		System.out.println(Civilites.MADAME);


		// step 2 : utiliser les Generics
		System.out.println(animaux);
		System.out.println(personnes);
		System.out.println(etresHumains);

		// step 3 : utiliser une lambda
		System.out.println(trierParPrenom(etresHumains));

		// step 4 : utiliser la Stream API
		System.out.println(compter(etresHumains));

		// step 5 : utiliser les filtres avec la Stream API
		System.out.println(compter(personnes, "Siber"));

		// step 6 : utiliser le mapping avec la Stream API
		// step 7 : utiliser une référence de méthode
		// step 8 : gérer les doublons
		// step 9 : trier
		System.out.println(listerPrenoms(etresHumains));

		// step 10 : naviguer dans une relation avec la Stream API
		// step 11 : rendre l'adresse facultative
		System.out.println(listerVilles(personnes));

		// step 12 : naviguer dans une collection avec la Stream API
		System.out.println(listerRaces(personnes));

		// step 13 : compter le nombre d'habitant par ville
		// step 14 : inclure dans le décompte le nombre d'habitants sans adresse
		System.out.println(compterHabitantsParVille(personnes));

		// step 15 : mettre en place Maven et s'assurer que le projet builde en dehors de l'IDE
		// step 16 : ajouter avec Maven la librairie commons lang, utiliser StringUtils.capitalize()
		// dans le code et s'assurer que le projet builde en dehors de l'IDE
		try {
			System.out.println(StringUtils.capitalize("hello world"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// step 17 : ajouter JUnit 5, s'assurer qu'on peut exécuter un test dans l'IDE,
		// et que le test est bien aussi exécuté lors de l'execution d'un mvn clean test
	}

}
