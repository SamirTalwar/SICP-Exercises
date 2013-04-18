(ns sicp.chapter-2-1.exercise-12
  (:use clojure.test)
  (:require [clojure.contrib.generic.arithmetic :as arithmetic]
            [sicp.comparisons :refer [approximately?]]))

(defprotocol Interval
  (lower [this])
  (upper [this])
  (center [this])
  (width [this])
  (percent [this]))

(defrecord NumericInterval [lower upper]
  Interval
  (center [this]
    (/ (+ lower upper) 2))
  (width [this]
    (/ (- upper lower) 2))
  (percent [this]
    (/ (.width this) (.center this))))

(def interval #(NumericInterval/create %))

(defn centered-interval [{:keys [center tolerance]}]
  (interval {:lower (- center tolerance), :upper (+ center tolerance)}))

(defn percentage-interval [{:keys [center tolerance]}]
  (centered-interval {:center center, :tolerance (* center tolerance)}))

(deftest exercise-12
         (is (= 7 (.center (interval {:lower 4, :upper 10}))))
         (is (= 3 (.width (interval {:lower 4, :upper 10}))))

         (is (= 5 (.center (centered-interval {:center 5, :tolerance 2}))))
         (is (= 4 (.width (centered-interval {:center 6, :tolerance 4}))))

         (is (approximately? 10.0 (.center (percentage-interval {:center 10, :tolerance 0.05}))))
         (is (approximately? 0.16 (.width (percentage-interval {:center 8, :tolerance 0.02}))))
         (is (approximately? 0.0625 (.percent (percentage-interval {:center 500, :tolerance 0.0625})))))
