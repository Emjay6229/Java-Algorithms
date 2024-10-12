package com.algorithms;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/* Each args member has a divisors. We want to compute a set of divisors for each args member.
 * Store each divisor set in a key-value (to allow easy retrieval)
 * Retrieve the sets and find its intersection.
 * Which gives us the Greatest Common Divisor.
 */
public class GreatestCommonDivisor {
    private static final Logger logger = Logger.getLogger(GreatestCommonDivisor.class.toString());
    private static final Level info = Level.INFO;

    public static void main(String[] args) {
        logger.log(info, "Attempting to compute greatest common divisor of " + Arrays.toString(args));

        if (args.length < 2) {
            logger.log(info, "Please provide at least two integers.");
            return;
        }

        Map<String, Set<Integer>> divisorMap = new HashMap<>();

        for (String arg : args) {
            try {
                divisorMap.put(arg, computeDivisors(Integer.parseInt(arg)));
            } catch (NumberFormatException e) {
                logger.log(Level.SEVERE, "Invalid input format " + e.getMessage());
                return;
            }
        }

        logger.log(info, "Divisor Map: " + divisorMap);

        // find the common number in all sets using set intersection
        Optional<Set<Integer>> commonDivisorsOptional = divisorMap.values()
            .stream()
            .reduce((s1, s2) -> {
                    s1.retainAll(s2);
                    return s1;
                });

        // Find the maximum divisor in the set of divisors
        if (commonDivisorsOptional.isEmpty() || commonDivisorsOptional.get().isEmpty()) {
            logger.log(info, "No Common Divisor is present");
        } else {
            int gcd = Collections.max(commonDivisorsOptional.get());
            logger.log(info, "Greatest Common Divisor is " + gcd); }
        }

    private static Set<Integer> computeDivisors(int x) {
        Set<Integer> setOfDivisors = new HashSet<>();
        for (int i = 1; i <= x; i++) {
            if (x % i == 0) setOfDivisors.add(i);
        }
        return setOfDivisors;
    }
}

