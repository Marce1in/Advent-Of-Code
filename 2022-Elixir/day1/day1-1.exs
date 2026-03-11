{:ok, values} = File.read("input")

values
|> IO.inspect(label: "Valor seco")
|> String.split("\n\n", trim: true)
|> IO.inspect(label: "Valores splitado por linhas")
|> Enum.map(&String.split(&1, "\n", trim: true))
|> IO.inspect(label: "Todos valores splitado")
|> Enum.map(fn row ->
  Enum.map(row, &String.to_integer/1)
  |> IO.inspect(label: "Valores em int")
  |> Enum.sum()
end)
|> IO.inspect(label: "Valores em somados")
|> Enum.max
|> IO.inspect(label: "Valor mais alto")
