package ro.catta.interview.ssc;

public class Encoder {

	public String encode(String input) {

		if (input == null || input.length() == 0) {
			throw new IllegalArgumentException("input parameter is mandatory.");
		}

		StringBuilder collector = new StringBuilder();
		char[] chars = input.toCharArray();

		char last = chars[0];
		long count = 0;

		for (char c : chars) {
			if (c == last) {
				count++;
			} else {
				collector.append(last).append(count);
				last = c;
				count = 1;
			}
		}

		collector.append(last).append(count);

		return collector.toString();
	}

	public String encodeWithStream(String input) {
		if (input == null || input.length() == 0) {
			throw new IllegalArgumentException("input parameter is mandatory.");
		}

		EncoderCollector encoderCollector = input.chars().collect(EncoderCollector::new, EncoderCollector::append,
				(left, right) -> {
					throw new RuntimeException("combiner not supported");
				});
		encoderCollector.finish();
		return encoderCollector.toString();

	}

	static class EncoderCollector {
		private StringBuilder collector = new StringBuilder();
		private char last = 0;
		private long count;
		private boolean finished;

		public void append(int c) {
			if (last == 0) {
				last = (char) c;
				count = 1;
			} else if (c == last) {
				count++;
			} else {
				collector.append(last).append(count);
				last = (char) c;
				count = 1;
			}
		}

		public void finish() {
			if (!finished) {
				collector.append(last).append(count);
				finished = true;
			}
		}

		@Override
		public String toString() {
			finish();
			return collector.toString();
		}

	}
}
