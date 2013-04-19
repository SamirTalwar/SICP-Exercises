(ns sicp.chapter-1-3.exercise-37
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]
            [sicp.comparisons :refer [approximately?]]))

(defn cont-frac-recursive [n d k]
  (letfn [(recurse [i]
            (if (> i k)
              0
              (/ (n i) (+ (d i) (recurse (inc i))))))]
    (recurse 1)))

(defn cont-frac-iterative [n d k]
  (letfn [(iter [denom k]
            (let [current-value (/ (n k) (denom k))]
              (if (= k 1)
                current-value
                (recur (fn [i] (+ (d i) current-value)) (dec k)))))]
    (iter d k)))

(def golden-ratio (/ (inc (math/sqrt 5)) 2))

(deftest exercise-37
         (is (approximately? (/ 1 golden-ratio) (cont-frac-recursive (fn [i] 1)
                                                                     (fn [i] 1)
                                                                     10)))
         (is (approximately? (/ 1 golden-ratio) (cont-frac-iterative (fn [i] 1)
                                                                     (fn [i] 1)
                                                                     10))))
