(ns sicp.chapter-2-1.exercise-06
  (:use clojure.test))

(defn zero [f]
  identity)

(defn add-1 [n]
  (fn [f]
    (fn [x]
      (f ((n f) x)))))

(defn to-number [numeral]
  ((numeral inc) 0))

(deftest exercise-06
         (is (= 0 (to-number zero)))
         (is (= 1 (to-number (add-1 zero))))
         (is (= 2 (to-number (add-1 (add-1 zero))))))
