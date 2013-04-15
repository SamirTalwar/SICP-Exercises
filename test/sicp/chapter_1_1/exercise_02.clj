(ns sicp.chapter-1-1.exercise-02
  (:use clojure.test))

(def answer (/ -37 150))

(deftest exercise-02
         (is (= answer
                (/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
                   (* 3 (- 6 2) (- 2 7))))))
