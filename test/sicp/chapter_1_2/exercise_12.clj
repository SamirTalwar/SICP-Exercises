(ns sicp.chapter-1-2.exercise-12
  (:use clojure.test))

(defn pascal [row column]
  (cond (< column 1) 0
        (> column row) 0
        (= row 1) 1
        :else (+ (pascal (dec row) (dec column))
                 (pascal (dec row) column))))

(deftest exercise-12
         (is (= 1 (pascal 1 1)))
         (is (= 1 (pascal 2 1)))
         (is (= 1 (pascal 2 2)))
         (is (= 2 (pascal 3 2)))
         (is (= 3 (pascal 4 2)))
         (is (= 1 (pascal 4 4)))
         (is (= 6 (pascal 5 3)))
         (is (= 10 (pascal 6 4)))
         (is (= 5 (pascal 6 5)))
         (is (= 1 (pascal 6 6))))
