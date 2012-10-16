
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Vector;

public class Adven {

	public static void main(String[] args) {

		Vector chars = new Vector();

		displayMenu(chars);

		System.out.println("OK");
		return;

	}

	private static void displayMenu(Vector chars) {
		try {
			String s = "";
			char c = '\0';
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

			while ('q' != c && 'Q' != c) {
				System.out.println("MAIN MENU");
				System.out.println("  [N] New game");
				System.out.println("  [Q] Quit");
				System.out.print("Enter choice: ");

				// Get the input
				s = in.readLine();
				// Take only the first letter
				c = s.charAt(0);
				if ('Q' == c || 'q' == c) {
					return;
				}
				else if ('n' == c || 'N' == c) {
					displayCharCreationMenu(chars);
				}
			}
		} catch (IOException io_ex) {
			System.out.println(io_ex.getMessage());
			System.exit(1);
		}
		return;
	}

	private static void displayCharCreationMenu(Vector chars) {
		try {
			String s = "";
			char c = '\0';
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			while ('q' != c && 'Q' != c) {
				System.out.println("PARTY:");
				if (chars.size() == 0) {
					System.out.println("\tCurrently empty");
				} else {
					for (int i = 0; i < chars.size(); i++) {
						System.out.print(" [" + i + "] ");
						System.out.print(((PlayerCharacter)(chars.elementAt(i))).getName() + " (");
						System.out.println(((PlayerCharacter)(chars.elementAt(i))).getAlignment() + " ");
						System.out.println(((PlayerCharacter)(chars.elementAt(i))).getRace() + " ");
						System.out.println(((PlayerCharacter)(chars.elementAt(i))).getCharClass() + ")");
					}
				}
				System.out.println("CHARACTER CREATION MENU");
				System.out.println("  [C] Create new character");
				System.out.println("  [Q] Quit and return to Main Menu");
				System.out.print("Enter choice: ");
				// Get the input
				s = in.readLine();
				// Take only the first letter
				c = s.charAt(0);
				if ('Q' == c || 'q' == c) {
					return;
				}
				else if ('c' == c || 'C' == c) {
					createCharacter(chars);
				}
			}
		} catch (IOException io_ex) {
			System.out.println(io_ex.getMessage());
			System.exit(1);
		}
		return;
	}

	private static void createCharacter(Vector chars) {
		try {
			String s = "";
			char c = '\0';
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PlayerCharacter newchar = new PlayerCharacter();
			// set the gender
			while ('c' != c && 'C' != c) {
				System.out.println("CREATE A CHARACTER");
				System.out.println("Choose a gender for your character:");
				System.out.println("  [M] Male");
				System.out.println("  [F] Female");
				System.out.println("  [C] Cancel");
				System.out.print("Enter choice: ");
				// Get the input
				s = in.readLine();
				// Take only the first letter
				c = s.charAt(0);
				if ('m' == c || 'M' == c) {
					newchar.setGender("male");
					c = 'c';
				}
				else if ('f' == c || 'F' ==c) {
					newchar.setGender("female");
					c = 'c';
				}
				else if ('c' == c || 'C' == c) {
					return;
				}
			}
			c = '\0';
			// set the race
			while ('c' != c && 'C' != c) {
				System.out.println("Choose a race for your character:");
				System.out.println("  [H] Human");
				System.out.println("  [D] Dwarf");
				System.out.println("  [E] Elf");
				System.out.println("  [G] Gnome");
				System.out.println("  [A] Half-elf");
				System.out.println("  [O] Half-Orc");
				System.out.println("  [F] Hafling");
				System.out.println("  [C] Cancel");
				System.out.print("Enter choice: ");
				// Get the input
				s = in.readLine();
				// Take only the first letter
				c = s.charAt(0);
				if ('h' == c || 'H' == c) {
					newchar.setRace("human");
					c = 'c';
				}
				else if ('d' == c || 'D' ==c) {
					newchar.setRace("dwarf");
					c = 'c';
				}
				else if ('e' == c || 'E' ==c) {
					newchar.setRace("elf");
					c = 'c';
				}
				else if ('g' == c || 'G' ==c) {
					newchar.setRace("gnome");
					c = 'c';
				}
				else if ('a' == c || 'A' ==c) {
					newchar.setRace("half-elf");
					c = 'c';
				}
				else if ('o' == c || 'O' ==c) {
					newchar.setRace("half-orc");
					c = 'c';
				}
				else if ('f' == c || 'F' ==c) {
					newchar.setRace("halfling");
					c = 'c';
				}
				else if ('c' == c || 'C' == c) {
					return;
				}
			}
			c = '\0';

			rollStats(newchar);

			System.out.println("\n");
			System.out.print("Enter a name for the character: ");
			s = in.readLine();
			newchar.setName(s);

			chooseAlignment(newchar);
			chooseClass(newchar);

			chars.add(newchar);

		} catch (IOException io_ex) {
			System.out.println(io_ex.getMessage());
			System.exit(1);
		}
		return;
	}

	private static void rollStats(PlayerCharacter ch) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s;
		char c;
		int tmp = -1;
		int rolls[] = {0,0,0,0,0,0};

		try {
			for (int i = 0; i < 6; i++) {
				rolls[i] = DiceRoller.roll("3d6");
			}
			System.out.println("Rolling stats...");
			System.out.print("You rolled:  ");
			for (int i = 0; i < 6; i++) {
				System.out.print(rolls[i] + "  ");
			}
			System.out.print("\n");

			int i = 0;
			while (i < 6) {
				System.out.println("CURRENT STATS:");
				System.out.println(ch.getStatString());
				System.out.print("Remaining scores: ");
				for (int j = i; j < 6; j++) {
					System.out.print(rolls[j] + " ");
				}
				System.out.print("\n");
				System.out.println("Assign " + rolls[i] + " to which stat?");
				System.out.print("[S]tr, [C]on, [D]ex, [I]nt, [W]isdom, or C[h]r: ");
				s = in.readLine();
				c = s.charAt(0);
				if (c == 'S' || c == 's') {
					tmp = ch.getStrength();
					ch.setStrength(rolls[i]);
					rolls[i] = tmp;
				}
				else if (c == 'c' || c == 'C') {
					tmp = ch.getConstitution();
					ch.setConstitution(rolls[i]);
					rolls[i] = tmp;
				}
				else if (c == 'd' || c == 'D') {
					tmp = ch.getDexterity();
					ch.setDexterity(rolls[i]);
					rolls[i] = tmp;
				}
				else if (c == 'i' || c == 'I') {
					tmp = ch.getIntelligence();
					ch.setIntelligence(rolls[i]);
					rolls[i] = tmp;
				}
				else if (c == 'w' || c == 'W') {
					tmp = ch.getWisdom();
					ch.setWisdom(rolls[i]);
					rolls[i] = tmp;
				}
				else if (c == 'h' || c == 'H') {
					tmp = ch.getCharisma();
					ch.setCharisma(rolls[i]);
					rolls[i] = tmp;
				}
				else {
					tmp = -1;
				}

				if (tmp == 0) {
					i++;
				}
			}
		} catch (IOException io_ex) {
			System.out.println(io_ex.getMessage());
			System.exit(1);
		}
		return;
	}

	private static void printStats(PlayerCharacter ch) {
		System.out.println(ch.getStatString());
	}

	private static void chooseAlignment(PlayerCharacter ch) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		char c = '\0';
		try {
			while ('\0' == c) {
				System.out.println("CHOOSE ALIGNMENT");
				System.out.println(" [1] Lawful Good");
				System.out.println(" [2] Lawful Neutral");
				System.out.println(" [3] Neutral Goodl");
				System.out.println(" [4] Neutral");
				System.out.println(" [5] Chaotic Good");
				System.out.println(" [6] Chaotic Neutral");
				System.out.print("Choice: ");
				s = in.readLine();
				c = s.charAt(0);
				if ('1' == c) {
					ch.setAlignment("lawful good");
				}
				else if ('2' == c) {
					ch.setAlignment("lawful neutral");
				}
				else if ('3' == c) {
					ch.setAlignment("neutral good");
				}
				else if ('4' == c) {
					ch.setAlignment("neutral");
				}
				else if ('5' == c) {
					ch.setAlignment("chaotic good");
				}
				else if ('6' == c) {
					ch.setAlignment("chaotic neutral");
				}
				else {
					// set c back to 0 to start loop again
					c = '\0';
				}
			}
		} catch (IOException io_ex) {
			System.out.println(io_ex.getMessage());
			System.exit(1);
		}
		return;
	}

	private static void chooseClass(PlayerCharacter ch) {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		char c = '\0';
		try {
			while ("" == s) {
				System.out.println("CHOOSE A CLASS");
				System.out.println(" [1] Barbarian");
				System.out.println(" [2] Bard");
				System.out.println(" [3] Cleric");
				System.out.println(" [4] Druid");
				System.out.println(" [5] Fighter");
				System.out.println(" [6] Monk");
				System.out.println(" [7] Paladin");
				System.out.println(" [8] Ranger");
				System.out.println(" [9] Rogue");
				System.out.println(" [10] Sorcerer");
				System.out.println(" [11] Wizard");
				System.out.print("Choice: ");
				s = in.readLine();
				if (s.equals("1")) {
					ch.setCharClass("barbarian");
				}
				else if (s.equals("2")) {
					ch.setCharClass("bard");
				}
				else if (s.equals("3")) {
					ch.setCharClass("cleric");
				}
				else if (s.equals("4")) {
					ch.setCharClass("druid");
				}
				else if (s.equals("5")) {
					ch.setCharClass("fighter");
				}
				else if (s.equals("6")) {
					ch.setCharClass("monk");
				}
				else if (s.equals("7")) {
					ch.setCharClass("paladin");
				}
				else if (s.equals("8")) {
					ch.setCharClass("ranger");
				}
				else if (s.equals("9")) {
					ch.setCharClass("rogue");
				}
				else if (s.equals("10")) {
					ch.setCharClass("sorcerer");
				}
				else if (s.equals("11")) {
					ch.setCharClass("wizard");
				}
				else {
					System.out.println("Invalid choice: " + s);
					s = "";
				}
			}
		} catch (IOException io_ex) {
			System.out.println(io_ex.getMessage());
			System.exit(1);
		}
		return;

	}

}
