(ns sicp.chapter-1-2.exercise-17
  (:use clojure.test))

(defn multiply [b n]
  (defn double-value [x] (+ x x))
  (defn halve [x] (/ x 2))
  (cond (= n 0) 0
        (even? n) (double-value (multiply b (halve n)))
        :else (+ b (multiply b (dec n)))))

(deftest exercise-17
         (is (= 1 (multiply 1 1)))
         (is (= 3 (multiply 1 3)))
         (is (= 6 (multiply 6 1)))
         (is (= 6 (multiply 3 2)))
         (is (= 12 (multiply 6 2)))
         (is (= 20 (multiply 5 4)))
         (is (= 27 (multiply 9 3)))
         (is (= 20 (multiply 2 10)))
         (is (= 25 (multiply 5 5)))
         (is (= 72 (multiply 12 6))))
