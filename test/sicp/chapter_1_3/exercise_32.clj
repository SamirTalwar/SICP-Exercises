(ns sicp.chapter-1-3.exercise-32
  (:use clojure.test))

(defn accumulate-recursive [combiner null-value term a next-value b]
  (if (> a b)
    null-value
    (combiner (term a) (accumulate-recursive combiner null-value term (next-value a) next-value b))))

(defn accumulate-iterative [combiner null-value term a next-value b]
  (defn iter [a result]
    (if (> a b)
      result
      (iter (next-value a) (combiner result (term a)))))
  (iter a null-value))

(def accumulate accumulate-iterative)

(defn sum [term a next-value b]
  (accumulate + 0 term a next-value b))

(defn product [term a next-value b]
  (accumulate * 1 term a next-value b))

(defn sum-cubes [a b]
  (sum (fn [n] (* n n n)) a inc b))

(defn sum-integers [a b]
  (sum identity a inc b))

(defn pi-sum [a b]
  (defn pi-term [x]
    (/ 1.0 (* x (+ x 2))))
  (defn pi-next [x]
    (+ x 4))
  (sum pi-term a pi-next b))

(defn factorial [n]
  (product identity 1 inc n))

(defn pi-product [n]
  (defn pi-approximation [^double n]
    (/ (+ n (rem n 2))
       (+ n (- 1 (rem n 2)))))
  (product pi-approximation 2 inc n))

(defn approximately? [a b]
  (< (Math/abs (- a b)) 0.01))

(deftest exercise-32
         (is (= 3025 (sum-cubes 1 10)))
         (is (= 55 (sum-integers 1 10)))
         (is (approximately? Math/PI (* 8 (pi-sum 1 1000))))

         (is (= 3628800 (factorial 10)))
         (is (approximately? Math/PI (* 4 (pi-product 1000)))))
