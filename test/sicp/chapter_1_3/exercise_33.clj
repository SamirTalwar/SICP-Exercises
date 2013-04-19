(ns sicp.chapter-1-3.exercise-33
  (:use clojure.test))

(defn smallest-divisor [n]
  (letfn [(divides? [a b] (= (rem b a) 0))
          (find-divisor [n test-divisor]
            (cond (> (* test-divisor test-divisor) n) n
                  (divides? test-divisor n) test-divisor
                  :else (find-divisor n (inc test-divisor))))]
    (find-divisor n 2)))

(defn prime? [n]
  (= n (smallest-divisor n)))

(defn coprime? [a b]
  (= 1 (.gcd (BigInteger/valueOf a) (BigInteger/valueOf b))))


(defn filtered-accumulate [predicate combiner null-value term a next-value b]
  (if (> a b)
    null-value
    (combiner
      (if (predicate a) (term a) null-value)
      (filtered-accumulate predicate combiner null-value term (next-value a) next-value b))))

(defn sum-of-squares-of-primes [a b]
  (filtered-accumulate prime? + 0 (fn [n] (* n n)) a inc b))

(defn product-of-smaller-coprimes [n]
  (filtered-accumulate (partial coprime? n) * 1 identity 1 inc (dec n)))

(deftest exercise-33
         (is (= 499269 (sum-of-squares-of-primes 100 200)))
         (is (= 385 (product-of-smaller-coprimes 12))))
