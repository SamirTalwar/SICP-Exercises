(ns sicp.chapter-2-1.exercise-03
  (:use clojure.test)
  (:require [clojure.contrib.math :as math]))

(defrecord Point [x y])

(defprotocol Shape
  (perimeter [this])
  (area [this]))

(defrecord Rectangle [top-left top-right bottom-right bottom-left]
  Shape
  (perimeter [this]
    (def width (- (.x top-right) (.x top-left)))
    (def height (- (.y top-left) (.y bottom-left)))
    (+ width height width height))
  (area [this]
    (def width (- (.x top-right) (.x top-left)))
    (def height (- (.y top-left) (.y bottom-left)))
    (* width height)))

(deftest exercise-03
         (def rectangle-a (Rectangle. (Point. 5 10) (Point. 10 10) (Point. 10 5) (Point. 5 5)))
         (is (= 20 (.perimeter rectangle-a)))
         (is (= 25 (.area rectangle-a)))

         (def rectangle-b (Rectangle. (Point. -3 2) (Point. 6 2) (Point. 6 -9) (Point. -3 -9)))
         (is (= 40 (.perimeter rectangle-b)))
         (is (= 99 (.area rectangle-b))))
