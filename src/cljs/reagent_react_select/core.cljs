(ns reagent-react-select.core
    (:require [reagent.core :as r :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [cljsjs.classnames]
              [cljsjs.react-input-autosize]
              [cljsjs.react-select]))


(defonce value (r/atom nil))
;; -------------------------
;; Views

(defn home-page []

  [:div [:h2 "Welcome to reagent-react-select"]
   [:link
    {:href
     "https://cdnjs.cloudflare.com/ajax/libs/react-select/1.2.1/react-select.min.css",
     :rel "stylesheet"}]
   [root-ui]
   [:div [:a {:href "/about"} "go to about page"]]])

(defn about-page []
  [:div [:h2 "About reagent-react-select"]
   [:div [:a {:href "/"} "go to the home page"]]])

(defn select-ui []
  [:> js/window.Select {:value @value
                        :options #js [#js {:value "a" :label "alpha"}
                                      #js {:value "b" :label "beta"}
                                      #js {:value "c" :label "gamma"}]
                        :onChange #(reset! value (aget % "value"))}])

(defn root-ui []
  [:div {:style {:width 400}}
   [:h3 "Select test"]
   [select-ui]])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
