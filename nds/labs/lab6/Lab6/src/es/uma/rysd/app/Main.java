package es.uma.rysd.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import es.uma.rysd.entities.Person;
import es.uma.rysd.entities.World;

public class Main {

    private static Random rand;
    private static Scanner sc;
    private final static String proxy = "proxy.lcc.uma.es";
    private final static String proxy_port = "3128";

    public static void main(String[] args) {
        // Uncomment the following lines if you are testing in the lab and accessing the Internet using the proxy
        // System.setProperty("https.proxyHost",proxy);
        // System.setProperty("https.proxyPort",proxy_port);

        SWClient sw = new SWClient();
        String response = null;
        rand = new Random();
        sc = new Scanner(System.in);

        do {
            whoIsHeavier(sw);
            //tallest(sw);
            //whoBornIn1(sw);
            //whoBornIn2(sw);
            System.out.println("Another round (y/n)?");
            response = sc.nextLine();
        } while (response.equals("y"));
        sc.close();

    }

    // Generates a number between 0 and max-1 that has not been used previously (the used numbers are in l)
    public static Integer getRandomResource(int max, List<Integer> l) {
        if (max == l.size()) {
            return null;
        }

        Integer p = rand.nextInt(max);
        while (l.contains(p)) {
            p = (p + 1) % max;
        }
        return p;
    }

    // Question that obtains two identical resources (characters in this case) and compares them
    public static void tallest(SWClient sw) {
        // Getting the number of stored people
        int max_people = sw.countResources("people");
        if (max_people == 0) {
            System.out.println("No people found.");
            return;
        }

        System.out.println("Generating new question...");
        // Picking two random people without repeating
        List<Integer> used = new ArrayList<Integer>();
        List<Person> people = new ArrayList<Person>();
        int counter = 0;
        while (counter < 2) {
            Integer p = getRandomResource(max_people, used);
            Person person = sw.getPerson(sw.buildResourceUrl("people", p));
            if (person == null) {
                System.out.println("There was an error in finding the resource " + p);
            } else {
                people.add(person);
                counter++;
            }
            used.add(p);
        }

        // Writing the question and reading the option
        Integer n = null;
        do {
            System.out.println("Who is taller? [0] " + people.get(0).name + " or [1] " + people.get(1).name);
            try {
                n = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                n = -1;
            }
        } while (n != 0 && n != 1);

        // Showing information about the chosen characters
        for (Person p : people) {
            System.out.println(p.name + " measures " + p.height);
        }

        // Result
        if (Double.parseDouble(people.get(n).height) >= Double.parseDouble(people.get((n + 1) % 2).height)) {
            System.out.println("Congratulations!!! " + success[rand.nextInt(success.length)]);
        } else {
            System.out.println("You failed :( " + error[rand.nextInt(error.length)]);
        }
    }

    // Question that relates multiple resources:
    // - Chooses a resource (planet in this case)
    // - Asks about a related resource (person who was born or created there)
    // - Searches for that resource and checks if it is related to the first one (if the searched person has the original planet)
    public static void whoBornIn1(SWClient sw) {
        // Getting the number of planets
        int max_planet = sw.countResources("planets");
        if (max_planet == 0) {
            System.out.println("No planets were found.");
            return;
        }

        System.out.println("Generating new question...");
        // Getting the planet (that has people)
        List<Integer> used = new ArrayList<Integer>();
        World world = null;
        do {
            Integer p = getRandomResource(max_planet, used);
            world = sw.getWorld(sw.buildResourceUrl("planets", p));
            if (world == null) {
                System.out.println("There was an error in finding the resource " + p);
            }
            used.add(p);
        } while (world == null || world.residents == null || world.residents.length < 1 || world.name.equals("unknown"));

        // Posing the question
        String s = null;
        System.out.println("Who was born or created in " + world.name + "?");
        s = sc.nextLine();
        // Searching for the indicated person
        Person p = sw.searchPersonByName(s);

        // Validating the answer and displaying their information
        if (p == null) {
            System.out.println("There is no one by that name");
        } else {
            System.out.println(p.name + " was born in " + p.homeplanet.name);
        }

        // Results
        if (p != null && p.homeplanet.name.equals(world.name)) {
            System.out.println("Congratulations!!! " + success[rand.nextInt(success.length)]);
        } else {
            System.out.println("You failed :( " + error[rand.nextInt(error.length)]);
        }
    }

    // Similar to the previous one but instead of asking to write, alternatives are offered:
// - A correct person from the planet is chosen randomly from the available ones
// - Three others that are not related to the resource are chosen randomly (the incorrect ones)
// - The correct one is inserted randomly
    public static void whoBornIn2(SWClient sw) {

        // Getting the number of planets and people
        int max_people = sw.countResources("people");
        int max_planet = sw.countResources("planets");
        if (max_people == 0 || max_planet == 0) {
            System.out.println("No people or planets found.");
            return;
        }

        System.out.println("Generating new question...");
        // Getting the planet (with people)
        List<Integer> used = new ArrayList<Integer>();
        World world = null;
        do {
            Integer p = getRandomResource(max_planet, used);
            world = sw.getWorld(sw.buildResourceUrl("planets", p));
            if (world == null) {
                System.out.println("There was an error in finding the resource " + p);
            }
            used.add(p);
        } while (world == null || world.residents == null || world.residents.length < 1 || world.name.equals("unknown"));
        used.clear();
        // Picking one randomly as the correct answer
        String[] residents = world.residents;
        Person correct = sw.getPerson(residents[rand.nextInt(residents.length)]);
        // Marking all people from the planet as used so they won't be selected again
        for (String s : residents) {
            used.add(sw.extractIdFromUrl(s));
        }

        // Finding 3 more
        List<Person> people = new ArrayList<Person>();
        int contador = 0;
        while (contador < 3) {
            Integer p = getRandomResource(max_people, used);
            Person person = sw.getPerson(sw.buildResourceUrl("people", p));
            if (person == null) {
                System.out.println("There was an error in finding the resource " + p);
            } else {
                people.add(person);
                contador++;
            }
            used.add(p);
        }
        // Inserting the correct one randomly
        int pos_correct = rand.nextInt(4);
        people.add(pos_correct, correct);

        // Reading the option
        Integer n = null;
        do {
            System.out.print("Who was born or made in " + world.name + "?");
            for (int i = 0; i < 4; i++) {
                System.out.print(" [" + i + "] " + people.get(i).name);
            }
            System.out.println();
            try {
                n = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                n = -1;
            }
        } while (n < 0 || n > 3);

        // Displaying the results
        for (Person p : people) {
            System.out.println(p.name + " was born in " + p.homeplanet.name);
        }

        // Results
        if (n == pos_correct) {
            System.out.println("Congratulations!!! " + success[rand.nextInt(success.length)]);
        } else {
            System.out.println("You failed :( " + error[rand.nextInt(error.length)]);
        }
    }


    // Asks for who character (between 2 randomly selected) is heavier
    public static void whoIsHeavier(SWClient sw) {
        int max_people = sw.countResources("people");
        if (max_people == 0) {
            System.out.println("No people found.");
            return;
        }

        System.out.println("Generating new question...");
        // Picking two random people without repeating
        List<Integer> used = new ArrayList<Integer>();
        List<Person> people = new ArrayList<Person>();
        int counter = 0;
        while (counter < 2) {
            Integer p = getRandomResource(max_people, used);
            Person person = sw.getPerson(sw.buildResourceUrl("people", p));
            if (person == null || person.mass.equals("unknown") || person.mass.contains(",")) {
                System.out.println("There was an error in finding the resource " + p);
            } else {
                people.add(person);
                counter++;
            }
            used.add(p);
        }

        // Writing the question and reading the option
        Integer n = null;
        do {
            System.out.println("Who is heavier? [0] " + people.get(0).name + " or [1] " + people.get(1).name);
            try {
                n = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                n = -1;
            }
        } while (n != 0 && n != 1);

        // Showing information about the chosen characters
        for (Person p : people) {
            System.out.println(p.name + " weighs " + p.mass);
        }

        // Result
        if (Double.parseDouble(people.get(n).mass) >= Double.parseDouble(people.get((n + 1) % 2).mass)) {
            System.out.println("Congratulations!!! " + success[rand.nextInt(success.length)]);
        } else {
            System.out.println("You failed :( " + error[rand.nextInt(error.length)]);
        }
    }
    private static String[] success = {"This is the way",
        "You are one with the Force. The Force is with you",
        "May the Force be with you",
        "Nothing happens by accident",
        "Surely, wonderful your mind is",
        "When you left, I was but the apprentice. Now you are the master",
        "The Force is calling to you, let it in.",
        "Your midichlorian count must be very high",
        "A lesson learned is a lesson earned.",
        "You must believe in yourself or no one else will", "You must believe in yourself or no one else will",
        "The path to wisdom is easy for those who are not blinded by ego"};
    private static String[] error = {"The best teacher, fail is.",
        "Fear is the way to the Dark Side. Fear leads to anger, anger leads to hate, hate leads to suffering. I sense a lot of fear in you",
        "Your lack of faith is annoying",
        "The ability to talk does not make you intelligent",
        "Concentrate on the moment. Feel, do not think, use your instinct",
        "Do not try. Do it, or do not do it, but do not try",
        "Patience, use the Force. Think",
        "I feel a disturbance in the Force.",
        "The dark side is intensifying in you",
        "The first step in correcting a mistake is patience", "The first step in correcting a mistake is patience",
        "Overconfidence is the most dangerous of carelessness",
        "The path of ignorance is guided by fear"};

}
