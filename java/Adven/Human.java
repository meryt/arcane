public Human implements Race {
	private int age_in_days;
	private int days_left_to_live;	// first set when player becomes venerable

	private int strength;
	private int dexterity;
	private int constitution;
	private int intelligence;
	private int wisdom;
	private int charisma;


	private static final MIDDLE_AGE    = 35 * 365;
	private static final OLD_AGE       = 53 * 365;
	private static final VENERABLE_AGE = 70 * 365;

	public int incrementAge(int num_days) {
		setAge(age_in_days + num_days);
		return getAge();
	}
	public void setAge(int num_days) {
		int prev_age = age_in_days;
		// Modify some stats when age passes certain milestones
		if (prev_age < MIDDLE_AGE && num_days >= MIDDLE_AGE) {
			strength -= 1;
			dexterity -= 1;
			constitution -= 1;
			intelligence += 1;
			wisdom += 1;
			charisma += 1;
		}
		else if (prev_age < OLD_AGE && num_days >= OLD_AGE) {
			strength -= 2;
			dexterity -= 2;
			constitution -= 2;
			intelligence += 1;
			wisdom += 1;
			charisma += 1;
		}
		else if (prev_age < VENERABLE_AGE && num_days >= VENERABLE_AGE) {
			strength -= 3;
			dexterity -= 3;
			constitution -= 3;
			intelligence += 1;
			wisdom += 1;
			charisma += 1;
			// Calculate time left to live once player reaches venerable age
			days_left_to_live = DiceRoller.roll("2d20") * 365 + DiceRoller.roll("1d364");
			// remove some days from days left to live if we are incrementing the age
			// by more than one day
			days_left_to_live -= num_days - VENERABLE_AGE;
		}

		age_in_days = num_days;
	}
	public int getAge() {
		return age_in_days;
	}

}
