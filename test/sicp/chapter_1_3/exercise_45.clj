(ns sicp.chapter-1-3.exercise-45
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]
            [sicp.comparisons :refer [approximately?]]
            [clojure.math.numeric-tower :as numeric-tower]))

(defn fixed-point-builder [strategy]
  (fn [f first-guess]
    (defn attempt [guess explosion-limit]
      (if (= explosion-limit 0)
        ; TODO: Use a better exception class than `java.lang.Exception`.
        (throw (Exception. "Infinite recursion alert.")))
      (let [next-guess (f guess)]
        (if (approximately? guess next-guess)
          next-guess
          (recur (strategy guess next-guess) (dec explosion-limit)))))
    (attempt first-guess 100)))

(defn repeated [f n]
  ((fn [func times]
     (cond (= times 1) func
           :else (recur (comp f func) (dec times))))
     f n))

(defn average-damp [guess next-guess]
  (/ (+ guess next-guess) 2))

(defn average-damp-n [n]
  (fn [guess next-guess] ((repeated #(average-damp guess %) n) next-guess)))

(defn nth-root [root value]
  (defn iteration [y] (/ value (numeric-tower/expt y (dec root))))
  (def damp-count (int (/ (Math/log root) (Math/log 2))))
  (if (> damp-count 1)
    (is (thrown-with-msg? Exception #"Infinite recursion" ((fixed-point-builder (average-damp-n (dec damp-count))) iteration 1.0))))
  ((fixed-point-builder (average-damp-n damp-count)) iteration 1.0))

(deftest exercise-45
         (is (approximately? (numeric-tower/expt 2 1/2) (nth-root 2 2)))
         (is (approximately? (numeric-tower/expt 2 1/3) (nth-root 3 2)))
         (is (approximately? (numeric-tower/expt 2 1/4) (nth-root 4 2)))
         (is (approximately? (numeric-tower/expt 2 1/5) (nth-root 5 2)))
         (is (approximately? (numeric-tower/expt 2 1/6) (nth-root 6 2)))
         (is (approximately? (numeric-tower/expt 2 1/7) (nth-root 7 2)))
         (is (approximately? (numeric-tower/expt 2 1/8) (nth-root 8 2)))
         (is (approximately? (numeric-tower/expt 2 1/9) (nth-root 9 2)))
         (is (approximately? (numeric-tower/expt 2 1/10) (nth-root 10 2)))
         (is (approximately? (numeric-tower/expt 2 1/20) (nth-root 20 2)))
         (is (approximately? (numeric-tower/expt 2 1/30) (nth-root 30 2)))
         (is (approximately? (numeric-tower/expt 2 1/40) (nth-root 40 2)))
         (is (approximately? (numeric-tower/expt 2 1/50) (nth-root 50 2)))
         (is (approximately? (numeric-tower/expt 2 1/60) (nth-root 60 2)))
         (is (approximately? (numeric-tower/expt 2 1/70) (nth-root 70 2)))
         (is (approximately? (numeric-tower/expt 2 1/80) (nth-root 80 2)))
         (is (approximately? (numeric-tower/expt 2 1/90) (nth-root 90 2)))
         (is (approximately? (numeric-tower/expt 2 1/100) (nth-root 100 2)))

         (is (approximately? (numeric-tower/expt 5 1/2) (nth-root 2 5)))
         (is (approximately? (numeric-tower/expt 5 1/5) (nth-root 5 5)))
         (is (approximately? (numeric-tower/expt 5 1/10) (nth-root 10 5)))
         (is (approximately? (numeric-tower/expt 5 1/20) (nth-root 20 5)))
         (is (approximately? (numeric-tower/expt 5 1/50) (nth-root 50 5))))
