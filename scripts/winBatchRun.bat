@echo on


cd jars



start cmd /k "title eureka  && start javaw -jar forum_eureka.jar"

start cmd /k "title web && start javaw -jar forum_web.jar"

start cmd /k "title user &&start javaw -jar forum_user.jar"

pause


