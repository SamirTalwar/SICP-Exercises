(ns sicp.chapter-1-3.exercise-31
  (:use clojure.test))

(defn product-recursive [term a next-value b]
  (if (> a b)
    1
    (* (term a) (product-recursive term (next-value a) next-value b))))

(defn product-iterative [term a next-value b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next-value a) (* result (term a)))))
  (iter a 1))

(def product product-iterative)

(defn factorial [n]
  (product identity 1 inc n))

(defn pi-product [n]
  (defn pi-approximation [^double n]
    (/ (+ n (rem n 2))
       (+ n (- 1 (rem n 2)))))
  (product pi-approximation 2 inc n))

(defn approximately? [a b]
  (< (Math/abs (- a b)) 0.01))

(deftest exercise-31
         (is (= 3628800 (factorial 10)))
         (is (approximately? Math/PI (* 4 (pi-product 1000)))))