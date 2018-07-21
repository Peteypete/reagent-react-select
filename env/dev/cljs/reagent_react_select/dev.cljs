(ns ^:figwheel-no-load reagent-react-select.dev
  (:require
    [reagent-react-select.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
