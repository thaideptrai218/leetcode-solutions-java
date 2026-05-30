/**
 * Math.
 *
 * <p>Recurring tools:
 * <ul>
 *   <li>GCD — Euclid: {@code gcd(a,b) = b==0 ? a : gcd(b, a%b)}. LCM: {@code a/gcd(a,b)*b} (avoids overflow).</li>
 *   <li>Modular arithmetic — {@code (a + b) % m}, {@code (a * b) % m}; for large powers use fast exponentiation.</li>
 *   <li>Prime sieve — Sieve of Eratosthenes, O(n log log n).</li>
 *   <li>Reservoir sampling — pick K uniformly from a stream of unknown length.</li>
 *   <li>Geometry — cross product sign for orientation; avoid floating point when possible.</li>
 *   <li>Catalan, Fibonacci, Pascal — recognize the sequence; closed forms exist for some.</li>
 * </ul>
 *
 * <p>Overflow: prefer {@code long} when multiplying two ints. Java {@code int} max is ~2.1 * 10^9.
 *
 * <p>Classic problems: 7, 9, 50, 60, 66, 67, 69, 168, 171, 172, 202, 204, 263, 264, 326, 367.
 */
package math;
