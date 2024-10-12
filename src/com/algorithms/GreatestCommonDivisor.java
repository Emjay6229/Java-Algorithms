package com.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GreatestCommonDivisor {
    private static final Logger logger = Logger.getLogger(GreatestCommonDivisor.class.toString());
    private static final Level info = Level.INFO;

    // main method
    public static void main(String[] args) {
        logger.log(info, "Attempting to compute greatest common divisor of " + Arrays.toString(args));

        /* each args member has a set of divisors.
         * We want to return `args.length` number of sets and find for a common number in all set.
         */
        if (args.length < 2) {
            logger.log(info, "Please provide at least two integers.");
            return;
        }

        Map<String, Set<Integer>> divisorMap = new HashMap<>();

        for (String arg : args) {
            try{
                divisorMap.put(arg, computeDivisors(Integer.parseInt(arg)));
            } catch(NumberFormatException e) {
                logger.log(Level.SEVERE, e.getMessage());
                return;
            }

        }

        logger.log(info, "Divisor Map: " + divisorMap);

        // find the common number in all sets using set intersection
        Optional<Set<Integer>> commonDivisorsOptional = divisorMap.values()
            .stream()
            .reduce(
                (s1, s2) -> {
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

