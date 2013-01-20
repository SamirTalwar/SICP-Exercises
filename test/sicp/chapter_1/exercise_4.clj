(ns sicp.chapter-1.exercise-4
  (:use clojure.test))

(defn a-plus-abs-b [a b]
  ((if (> b 0) + -) a b))

(deftest exercise-4
         (is (= 0 (a-plus-abs-b 0 0))) ; Adds `a` to `b`.
         (is (= 1 (a-plus-abs-b 1 0))) ; Adds `a` to `b`.
         (is (= 1 (a-plus-abs-b 0 1))) ; Adds `a` to `b`.
         (is (= -1 (a-plus-abs-b -1 0))) ; Adds `a` to `b`.
         (is (= 1 (a-plus-abs-b 0 -1)))) ; Subtracts `a` from `b` when `b` is negative.
