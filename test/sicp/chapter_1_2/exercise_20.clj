(ns sicp.chapter-1-2.exercise-20
  (:use clojure.test))

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (rem a b))))

(def all-equal? (partial apply =))

(deftest exercise-20
         (is (all-equal? [(gcd 206 40)
                          (gcd 40 (rem 206 40))
                          (gcd 6 (rem 40 6))
                          (gcd 4 (rem 6 4))
                          (gcd 2 (rem 4 2))
                          (gcd 2 0)
                          2])))

; Normal-order evaluation: There are four calls to `remainder`.
; Applicative-order evaluation: Infinite recursion, and so there would be theoretically infinite calls to `remainder`.
