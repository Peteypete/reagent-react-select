(ns reagent-react-select.prod
  (:require [reagent-react-select.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
