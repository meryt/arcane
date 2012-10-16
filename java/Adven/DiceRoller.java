
public class DiceRoller {

	public static int roll(String format) {
		int modifier = 0;
		int multiplier = 0;
		int die = 0;
		int result = 0;

		// TODO: proper error handling
		if (!(format.matches("[0-9]+[dD][0-9]+([+-][0-9]+)?"))) {
			System.out.print("Invalid format for die roll: " + format + "\n");
			return 0;
		}

		String spl[] = format.split("[dD]");
		multiplier = Integer.parseInt(spl[0]);

		modifier = 0;
		if (-1 < spl[1].indexOf('+')) {
			modifier = Integer.parseInt(spl[1].substring(spl[1].indexOf('+') + 1));
			die = Integer.parseInt(spl[1].substring(0,spl[1].indexOf('+')));
		}
		else if (-1 < spl[1].indexOf('-')) {
			modifier = Integer.parseInt(spl[1].substring(spl[1].indexOf('-')));
			die = Integer.parseInt(spl[1].substring(0,spl[1].indexOf('-')));
		} else {
			die = Integer.parseInt(spl[1]);
		}

		for (int i = 0; i < multiplier; i++) {
			result += Math.round((int)(Math.random()*die)) + 1;
		}
		result += modifier;

		return result;
	}

	public static void main(String[] args) {
		int result = 0;
		if (1 <= args.length) {
			result = roll(args[0]);
		} else {
			result = roll("2d12+2");
		}
		System.out.print("Rolled a " + result);
		return;
	}

}
