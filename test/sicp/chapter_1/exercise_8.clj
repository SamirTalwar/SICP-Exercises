(ns sicp.chapter-1.exercise-8
  (:use clojure.test))

(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ (+ (/ x (* guess guess)) (* 2 guess)) 3)))

(defn good-enough? [guess previous-guess]
  (< (Math/abs (- guess previous-guess)) 0.000001))

(defn cbrt-iter [guess previous-guess x]
  (if (good-enough? guess previous-guess)
    guess
    (cbrt-iter (improve guess x) guess x)))

(defn cbrt [x]
  (cbrt-iter 1.0 0.0 x))


(defn cube [x] (* x x x))

(defn almost-equal [a b]
  (< (Math/abs (- a b)) 0.001))

(deftest exercise-8
         (is (almost-equal 3.0 (cbrt 27.0)))
         (is (almost-equal 11.447142 (cbrt 1500.0)))
         (is (almost-equal 1.392850 (cbrt (+ (cbrt 2) (cbrt 3)))))
         (is (almost-equal 1000.0 (cube (cbrt 1000.0))))
         (is (almost-equal 0.001 (cbrt 0.000000001)))
         (is (almost-equal 2097152.0 (cbrt Long/MAX_VALUE))))
