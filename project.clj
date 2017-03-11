(defproject clockwork "0.1.2-SNAPSHOT"
  :description "A convenient time tracker"
  :url "http://100starlings.github.io/clockwork"
  :license {
    :name "Eclipse Public License"
    :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [org.clojure/clojurescript "1.9.473" :exclusions [org.apache.ant/ant]]
    [org.clojure/core.async "0.2.395"]
    [figwheel "0.5.9"]
    [reagent "0.6.0"]
    [re-frame "0.9.2"]
    [ring/ring-core "1.5.0"]
    [com.andrewmcveigh/cljs-time "0.5.0-alpha1"]
    [binaryage/devtools "0.9.2"]]
  :plugins [
    [lein-cljsbuild "1.1.5"  :exclusions [[org.clojure/clojure]]]
    [lein-externs "0.1.6"]
    [lein-shell "0.5.0"]
    [lein-figwheel "0.5.9" :exclusions [org.clojure/core.cache]]]
  :source-paths ["src_tools"]
  :aliases {
    "descjop-help" ["new" "descjop" "help"]
    "descjop-init" ["do"
                    ["shell" "npm" "install"]
                    ["shell" "grunt" "download-electron"]]
    "descjop-init-win" [
      "do"
      ["shell" "cmd.exe" "/c" "npm" "install"]
      ["shell" "cmd.exe" "/c" "grunt" "download-electron"]]
    "descjop-externs" [
      "do"
      ["externs" "dev-main" "app/dev/js/externs.js"]
      ["externs" "dev-front" "app/dev/js/externs_front.js"]
      ["externs" "prod-main" "app/prod/js/externs.js"]
      ["externs" "prod-front" "app/prod/js/externs_front.js"]]
    "descjop-externs-dev" [
      "do"
      ["externs" "dev-main" "app/dev/js/externs.js"]
      ["externs" "dev-front" "app/dev/js/externs_front.js"]]
    "descjop-externs-prod" [
      "do"
      ["externs" "prod-main" "app/prod/js/externs.js"]
      ["externs" "prod-front" "app/prod/js/externs_front.js"]]
    "descjop-figwheel" ["trampoline" "figwheel" "dev-front"]
    "descjop-once" [
      "do"
      ["cljsbuild" "once" "dev-main"]
      ["cljsbuild" "once" "dev-front"]
      ["cljsbuild" "once" "prod-main"]
      ["cljsbuild" "once" "prod-front"]]
    "descjop-once-dev" [
      "do"
      ["cljsbuild" "once" "dev-main"]
      ["cljsbuild" "once" "dev-front"]]
    "descjop-once-prod" [
      "do"
      ["cljsbuild" "once" "prod-main"]
      ["cljsbuild" "once" "prod-front"]]
    ;; electron packager for production
    "descjop-uberapp-osx" ["shell" "cmd.exe" "/c" "electron-packager" "./app/prod" "{{name}}" "--platform=darwin" "--arch=x64" "--electron-version=1.6.2"]
    "descjop-uberapp-app-store" ["shell" "cmd.exe" "/c" "electron-packager" "./app/prod" "{{name}}" "--platform=mas" "--arch=x64" "--electron-version=1.6.2"]
    "descjop-uberapp-linux" ["shell" "cmd.exe" "/c" "electron-packager" "./app/prod" "{{name}}" "--platform=linux" "--arch=x64" "--electron-version=1.6.2"]
    "descjop-uberapp-win64" ["shell" "cmd.exe" "/c" "electron-packager" "./app/prod" "{{name}}" "--platform=win32" "--arch=x64" "--electron-version=1.6.2"]
    "descjop-uberapp-win32" ["shell" "cmd.exe" "/c" "electron-packager" "./app/prod" "{{name}}" "--platform=win32" "--arch=ia32" "--electron-version=1.6.2"]}
  :hooks [leiningen.cljsbuild]
  :cljsbuild {
    :builds {
      :dev-main {
        :source-paths ["src"]
        :incremental true
        :jar true
        :assert true
        :compiler {
          :output-to "app/dev/js/cljsbuild-main.js"
          :externs [
            "app/dev/js/externs.js"
            "node_modules/closurecompiler-externs/path.js"
            "node_modules/closurecompiler-externs/process.js"]
          ;; :warnings true
          :elide-asserts true
          :target :nodejs

          ;; no optimize compile (dev)
          ;; :optimizations :none
          ;; when no optimize uncomment
          ;; :output-dir "app/dev/js/out"

          ;; simple compile (dev)
          :optimizations :simple

          ;; advanced compile (prod)
          ;;:optimizations :advanced

          ;;:source-map "app/dev/js/test.js.map"
          :pretty-print true
          :output-wrapper true}}
      :dev-front {
        :source-paths ["src_ui" "src_ui_profile/clockwork_ui/dev"]
        :incremental true
        :jar true
        :assert true
        :compiler {
          :output-to "app/dev/js/front.js"
          :externs ["app/dev/js/externs_front.js"]
          ;; :warnings true
          :elide-asserts true
          ;; :target :nodejs

          ;; when no optimize uncomment
          :output-dir "app/dev/js/out"
          ;; no optimize compile (dev)
          :optimizations :none

          ;; simple compile (dev)
          ;;:optimizations :simple

          ;; advanced compile (prod)
          ;;:optimizations :advanced

          :source-map true
          :source-map-timestamp true
          :pretty-print true
          :output-wrapper true

          :preloads [devtools.preload]
          :external-config {
            :devtools/config {:dont-detect-custom-formatters true}}}}
      :prod-main {
        :source-paths ["src"]
        :incremental true
        :jar true
        :assert true
        :compiler {
          :output-to "app/prod/js/cljsbuild-main.js"
          :externs [
            "app/prod/js/externs.js"
            "node_modules/closurecompiler-externs/path.js"
            "node_modules/closurecompiler-externs/process.js"]
          ;; :warnings true
          :elide-asserts true
          :target :nodejs

          ;; no optimize compile (dev)
          ;;:optimizations :none
          ;; when no optimize uncomment
          :output-dir "app/prod/js/out-main"

          ;; simple compile (dev)
          :optimizations :simple

          ;; advanced compile (prod)
          ;;:optimizations :advanced

          ;;:source-map "app/prod/js/test.js.map"
          :pretty-print true
          :output-wrapper true}}
      :prod-front {
        :source-paths ["src_front" "src_front_profile/clockwork_front/prod"]
        :incremental true
        :jar true
        :assert true
        :compiler {
          :output-to "app/prod/js/front.js"
          :externs ["app/prod/js/externs_front.js"]
          ;; :warnings true
          :elide-asserts true
          ;; :target :nodejs

          ;; no optimize compile (dev)
          ;;:optimizations :none
          ;; when no optimize uncomment
          :output-dir "app/prod/js/out"

          ;; simple compile (dev)
          :optimizations :simple

          ;; advanced compile (prod)
          ;;:optimizations :advanced

          ;;:source-map "app/prod/js/test.js.map"
          :pretty-print true
          :output-wrapper true}}}}
  :figwheel {
    :http-server-root "public"
    :ring-handler figwheel-middleware/app
    :server-port 3449})
