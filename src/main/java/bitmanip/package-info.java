/**
 * Bit Manipulation.
 *
 * <p>Core tricks:
 * <ul>
 *   <li>{@code x & 1} — last bit (parity).</li>
 *   <li>{@code x >> 1} / {@code x >>> 1} — divide by 2; use {@code >>>} for unsigned shift.</li>
 *   <li>{@code x & (x - 1)} — clears lowest set bit; loop counts set bits (Brian Kernighan).</li>
 *   <li>{@code x ^ x = 0}, {@code x ^ 0 = x} — finds the unique element among pairs.</li>
 *   <li>{@code x & -x} — isolates the lowest set bit.</li>
 *   <li>{@code 1 << i} — bitmask for position i; iterate subsets via mask = (mask - 1) & full.</li>
 *   <li>{@link Integer#bitCount(int)} — built-in popcount.</li>
 * </ul>
 *
 * <p>Watch out: Java {@code int} is 32-bit signed; use {@code long} or {@code >>>} when high bit matters.
 *
 * <p>Classic problems: 136, 137, 190, 191, 201, 231, 260, 268, 338, 371, 421.
 */
package bitmanip;
