This document outlines a solution for generating a final bill for listed items.

##########################################################

To create a custom bill, you need to add data to the JSON file. In the project, under the data package, there is a JSON file that serves as input to generate a bill for all the specified items. For testing purposes, I have included three sample entries for which the bill will be generated.

Please ensure that the input follows this specific structure:

```json
{
  "order_no": <bill number>,
  "order_items": [
    {
      "item_name": <name of item>,
      "item_category": <category of item ["food", "book", "medical", "other"]>,
      "item_value": <price of item>,
      "isImported": <boolean value whether it is imported from outside or not>,
      "item_count": <number of items>
    }
  ]
}
```

Here, the calculation is as follows: All items are taxed at 10% except food, book, and medical. Additionally, all imported items are taxed at 5%. We have applied the round-off function to 0.05.
