package narasimha.karumanchi.strings;

public class StringsMain {
	public static void main(String[] args) {
		final var stringsMain = new StringsMain();
		stringsMain.runFindFirstNonRepeatingCharIndex();
	}

	// .....Runners....
	public void runFindFirstNonRepeatingCharIndex() {
		final var str = "abdbccdea";
		System.out.println(findFirstNonRepeatingCharIndex(str));
	}

	// ....Algorithms...
	public int findFirstNonRepeatingCharIndex(String str) {
		final var countArr = new int[256];
		for (final char c : str.toCharArray()) {
			countArr[c - '0']++;
		}
		var index = 0;
		for (final char c : str.toCharArray()) {
			if (countArr[c - '0'] == 1) {
				return index;
			}
			index++;
		}
		return -1;
	}
}
