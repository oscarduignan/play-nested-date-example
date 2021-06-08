# Experimenting with play nested fields

Feeling out helpers we can provide to make it easier to work with govukDateInput, towards something like:

```
@govukDateInput(DateInput(...).withFormField(form("dateField")))
```

- ./app/views/Index.scala.html how we work with play nested fields to populate DateInput
- ./app/controllers/HomeController.scala how nested field can be setup