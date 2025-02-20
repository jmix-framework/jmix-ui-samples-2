Этот пример демонстрирует, как использовать inline-редактирование для уменьшения вложенности диалоговых окон.

Здесь вы можете увидеть ту же структуру данных, состоящую из сущностей `Airport`, `Terminal` и `MeetingPoint`, что и в примере с [двухуровневой композицией]({contextPath}/sample/composition-2-levels). На экране деталей аэропорта отображается список терминалов в редактируемой таблице данных. Кнопка **Show meeting points** позволяет пользователям просматривать и редактировать точки встречи выбранного терминала.

Детали реализации:

- [Airport.java]({currentPath}?tab=Airport.java)
  - Атрибут `Airport.terminals` аннотирован с помощью `@Composition`.

- [Terminal.java]({currentPath}?tab=Terminal.java)
  - Атрибут `Terminal.meetingPoints` аннотирован с помощью `@Composition`.

- [airport-detail-view.xml]({currentPath}?tab=airport-detail-view.xml)
  - Столбцы `terminalsDataGrid` имеют атрибут `editable="true"`. Таблица данных также содержит `editorActionsColumn` для открытия и закрытия inline-редактирования строки.
  - Действие `showMeetingPointsAction` программно открывает экран деталей выбранного терминала для отображения его точек встречи.

- [AirportDetailView.java]({currentPath}?tab=AirportDetailView.java) отображает поля аэропорта и редактируемую таблицу данных терминалов.
  - Обработчик `onTerminalsDataGridCreateAction()` переопределяет стандартное поведение `terminalsDataGrid.createAction`. Вместо открытия экрана деталей терминала он создает новый экземпляр `Terminal`, добавляет его в контейнер данных и активирует inline-редактирование в таблице данных.
  - Обработчик `onTerminalsDataGridShowMeetingPointsAction()` вызывается, когда пользователь нажимает кнопку **Show meeting points** или соответствующее действие в контекстном меню таблицы данных терминалов. Он открывает [TerminalDetailView]({currentPath}?tab=TerminalDetailView.java) и передает текущий `DataContext` как родительский. Этот код демонстрирует, как программно открыть вложенный экран композиции.

- [TerminalDetailView.java]({currentPath}?tab=TerminalDetailView.java)
  - Этот экран отображает точки встречи конкретного терминала. Остальные атрибуты терминала редактируются в таблице данных терминалов аэропорта.
  - Обработчик события `onReady()` обновляет заголовок диалога, добавляя имя редактируемого терминала.
  - Обработчик `onMeetingPointsDataGridCreateAction()` переопределяет стандартное поведение `meetingPointsDataGrid.createAction`. Он создает новый экземпляр точки встречи, добавляет его в контейнер коллекции и открывает inline-редактор таблицы данных.
  - Обработчик события `onMeetingPointsDcItemPropertyChange()` помечает корневую редактируемую сущность `Terminal` как измененную в `DataContext`, когда изменяется свойство точки встречи. Это гарантирует сохранение всех данных в родительский `DataContext`.
