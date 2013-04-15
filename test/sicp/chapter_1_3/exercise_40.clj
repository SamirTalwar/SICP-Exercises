(ns sicp.chapter-1-3.exercise-40
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]
            [sicp.comparisons :refer [approximately?]]))

(defn fixed-point [f first-guess]
  (defn attempt [guess]
    (let [next-guess (f guess)]
      (if (approximately? guess next-guess)
        next-guess
        (recur next-guess))))
  (attempt first-guess))

(defn newtons-method [g guess]
  (def dx 0.00001)
  (defn deriv [g]
    (fn [x] (/ (- (g (+ x dx)) (g x)) dx)))
  (defn newton-transform [g]
    (fn [x] (- x (/ (g x) ((deriv g) x)))))
  (fixed-point (newton-transform g) guess))

; x^3 + ax^2 + bx + c
(defn cubic [a b c]
  (defn cube [x] (* x x x))
  (defn square [x] (* x x))
  (fn [x] (+ (cube x) (* a (square x)) (* b x) c)))

(deftest exercise-40
         ; (x + 1)(x + 2)(x + 3)
         ; = x^3 + x^2 + 2x^2 + 3x^2 + 2x + 3x + 6x + 6
         ; = x^3 + 6x^2 + 11x + 6
         (is (approximately? -1 (newtons-method (cubic 6 11 6) 1)))
         (is (approximately? -3 (newtons-method (cubic 6 11 6) -10)))

         ; (x - 5)(x + 8)(x - 10)
         ; = x^3 - 5x^2 + 8x^2 - 10x^2 - 40x + 50x - 80x + 400
         ; = x^3 - 7x^2 -70x + 400
         (is (approximately? 10 (newtons-method (cubic -7 -70 400) 20)))
         (is (approximately? 5 (newtons-method (cubic -7 -70 400) 0)))
         (is (approximately? -8 (newtons-method (cubic -7 -70 400) -20))))
