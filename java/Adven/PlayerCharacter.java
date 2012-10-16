
public class PlayerCharacter {

	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;

	private String name;
	private String alignment;
	private int age_in_days;
	private int days_left_to_live;	// first set when player becomes venerable
	private String race;
	private int height_inches;
	private int weight_pounds;
	private String gender = new String();
	private String char_class;

	public static void main(String[] args) {
		// Create a character with default properties
		PlayerCharacter pc = new PlayerCharacter();
		// Print the character
		System.out.print(pc.toString());
	}

	public PlayerCharacter() {
		name = "Chana";
		race = new String("elf");
		char_class = new String("wizard");
		gender = "female";
		//age_in_days = randomize_age();
		setAge(randomize_age());
		randomize_height_weight();
		strength = 0;
		dexterity = 0;
		constitution = 0;
		intelligence = 0;
		wisdom = 0;
		charisma = 0;
	}

	public String toString() {
		String str = new String();
		str += name + "\n";
		str += gender + " " + alignment + " " + race + " " + char_class;
		str += ", aged " + (int)Math.floor(((float)age_in_days)/365.0) + " years " + age_in_days % 365 + " days\n";
		str += "Height: " + (int)Math.floor(((float)height_inches)/12) + "'" + height_inches % 12 + "\", ";
		str += "Weight: " + weight_pounds + " pounds\n";
		str += "STR: " + strength + "\n";
		str += "DEX: " + dexterity + "\n";
		str += "CON: " + constitution + "\n";
		str += "INT: " + intelligence + "\n";
		str += "WIS: " + wisdom + "\n";
		str += "CHR: " + charisma + "\n";
		return str;
	}

	/******************************************************************************
	 * randomize_age
	 * PURPOSE		generate a random age based on the race
	 * PRECONDITIONS	race must be a non-empty string matching a valid race
	 *								class must be a non-empty string matching a valid class
	 * POSTCONDITIONS	none
	 * RETURNS		The random age in days
	******************************************************************************/
	private int randomize_age() {
		if (0 == race.length() || 0 == char_class.length()) {
			System.out.print("You must set race and class before setting age.\n");
		}

		DataHash d = new DataHash("VitalStatistics");
		int baseage = Integer.parseInt(d.get("randomstartingage_base_" + race));
		String modifier = d.get("randomstartingage_modifier_" + race + "_" + char_class);
		return (baseage + DiceRoller.roll(modifier)) * 365 + DiceRoller.roll("1d364");

	}

	/******************************************************************************
	 * randomize_height_weight
	 * PURPOSE		generate a random height and weight based on race
	 * PRECONDITIONS	race must be a non-empty string matching a valid race
	 *								gender must be a non-empty string matching a valid gender
	 * POSTCONDITIONS	height and weight set to a non-negative value
	******************************************************************************/
	private void randomize_height_weight() {
		if (0 == race.length() || 0 == gender.length()) {
			System.out.print("You must set race and gender before setting weight.\n");
			return;
		}

		DataHash d = new DataHash("VitalStatistics");
		int baseheight = Integer.parseInt(d.get("baseheight_" + race + "_" + gender));
		String heightmod = d.get("heightmod_" + race + "_" + gender);
		int heightmodval = DiceRoller.roll(heightmod);
		height_inches = baseheight + heightmodval;
		int baseweight = Integer.parseInt(d.get("baseweight_" + race + "_" + gender));
		String weightmod = d.get("weightmod_" + race + "_" + gender);
		weight_pounds = baseweight + heightmodval * DiceRoller.roll(weightmod);
		return;
	}

	/******************************************************************************
	 * setAge
	 * PURPOSE		Sets the age
	******************************************************************************/
	public void setAge(int new_age_in_days) {
		int prev_age = age_in_days;
		int middle_age;
		int old_age;
		int venerable_age;
		String max_age_mod;

		DataHash d = new DataHash("VitalStatistics");
		middle_age = Integer.parseInt(d.get("agecutoff_middleage_" + race)) * 365;
		old_age = Integer.parseInt(d.get("agecutoff_oldage_" + race)) * 365;
		venerable_age = Integer.parseInt(d.get("agecutoff_venerableage_" + race)) * 365;
		max_age_mod = d.get("maximumageadder_" + race);

		// Modify some stats when age passes certain milestones
		String newage = new String();
		if (prev_age < middle_age && new_age_in_days >= middle_age) {
			newage = "middleage";
		}
		else if (prev_age < old_age && new_age_in_days >= old_age) {
			newage = "oldage";
		}
		else if (prev_age < venerable_age && new_age_in_days >= venerable_age) {
			newage = "venerableage";
		}

		if (0 < newage.length()) {
			strength += Integer.parseInt(d.get("statmod_strength_" + newage));
			dexterity += Integer.parseInt(d.get("statmod_dexterity_" + newage));
			constitution += Integer.parseInt(d.get("statmod_constitution_" + newage));
			intelligence += Integer.parseInt(d.get("statmod_intelligence_" + newage));
			wisdom += Integer.parseInt(d.get("statmod_wisdom_" + newage));
			charisma += Integer.parseInt(d.get("statmod_charisma_" + newage));
		}

		if (newage.equalsIgnoreCase("venerableage")) {
			// Calculate time left to live once player reaches venerable age
			days_left_to_live = DiceRoller.roll(d.get("maximumageadder_" + race)) * 365 + DiceRoller.roll("1d364");
			// remove some days from days left to live if we are incrementing the age
			// by more than one day
			days_left_to_live -= new_age_in_days - venerable_age;
		}
		age_in_days = new_age_in_days;
	}

	public void incrementAge(int days) {
		setAge(age_in_days + days);
	}

	public void setGender(String newgender) {
		gender = newgender;
	}
	public void setRace(String newrace) {
		race = newrace;
	}
	public String getRace() {
		return new String(race);
	}

	public String getStatString() {
		String s = new String();
		s += "STR " + strength + "\n";
		s += "CON " + constitution + "\n";
		s += "DEX " + dexterity + "\n";
		s += "INT " + intelligence + "\n";
		s += "WIS " + wisdom + "\n";
		s += "CHA " + charisma + "\n";
		return s;
	}

	public int getStrength() {
		return strength;
	}
	public void setStrength(int s) {
		strength = s;
	}
	public int getConstitution() {
		return constitution;
	}
	public void setConstitution(int c) {
		constitution = c;
	}
	public int getDexterity() {
		return dexterity;
	}
	public void setDexterity(int d) {
		dexterity = d;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int i) {
		intelligence = i;
	}
	public int getWisdom() {
		return wisdom;
	}
	public void setWisdom(int w) {
		wisdom = w;
	}
	public int getCharisma() {
		return charisma;
	}
	public void setCharisma(int c) {
		charisma = c;
	}
	public String getName() {
		return new String(name);
	}
	public void setName(String s) {
		name = new String(s);
	}
	public String getCharClass() {
		return new String(char_class);
	}
	public void setCharClass(String s) {
		char_class = new String(s);
	}
	public String getAlignment() {
		return new String(alignment);
	}
	public void setAlignment(String s) {
		alignment = new String(s);
	}

}
