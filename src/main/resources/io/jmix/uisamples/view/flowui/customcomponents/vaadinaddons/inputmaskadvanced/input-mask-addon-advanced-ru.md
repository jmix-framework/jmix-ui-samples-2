В данном примере демонстрируется продвинутая интеграция компонента `InputMask` предоставляемого аддоном Vaadin.\
Базовую интеграцию можно посмотреть в [другом примере]({contextPath}/sample/input-mask-addon-simple).

Чтобы полностью внедрить компонент в пользовательский интерфейс Jmix необходимо выполнить следующие шаги:

1. Реализовать композитный компонент на основе `TypedTextField` для оборачивания функциональности `InputMask` (см. [`MaskField.java`]({currentPath}?tab=MaskField.java))
2. Создать XSD схему для поддержки компонента в XML-дескрипторе (см. [`mask-field.xsd`]({currentPath}?tab=mask-field.xsd))
3. Реализовать загрузчик компонента из XML (см. [`MaskFieldLoader.java`]({currentPath}?tab=MaskFieldLoader.java))
4. Зарегистрировать компонент и загрузчик в приложении (см. [`MaskFieldRegistration.java`]({currentPath}?tab=MaskFieldRegistration.java))

После выполнения этих шагов вы сможете использовать компонент стандартным образом в Jmix UI.
