(ns sicp.chapter-2-1.exercise-06
  (:use clojure.test))

(defn zero [f]
  identity)

(defn add-1 [n]
  (fn [f]
    (fn [x]
      (f ((n f) x)))))

(defn one [f]
  (fn [x]
    (f x)))

(defn two [f]
  (fn [x]
    (f (f x))))

(defn add [a b]
  ((a add-1) b))

(defn to-number [numeral]
  ((numeral inc) 0))

(deftest exercise-06
         (is (= 0 (to-number zero)))
         (is (= 1 (to-number (add-1 zero))))
         (is (= 2 (to-number (add-1 (add-1 zero)))))

         (is (= 1 (to-number one)))
         (is (= 2 (to-number two)))

         (is (= 0 (to-number (add zero zero))))
         (is (= 2 (to-number (add one one))))
         (is (= 7 (to-number (add (add-1 (add-1 (add-1 zero))) (add-1 (add-1 (add-1 (add-1 zero)))))))))
