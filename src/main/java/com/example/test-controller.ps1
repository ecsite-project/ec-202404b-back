# Set the URL of the Spring Boot application
$baseUrl = "http://localhost:8080/items"

# Send a GET request to the endpoint
$response = Invoke-RestMethod -Uri $baseUrl -Method Get

# Check the status code
if ($response.StatusCode -eq 200) {
    Write-Output "Request succeeded with status code 200"
    Write-Output "Response body:"
    $response.Content | ConvertFrom-Json | Format-Table
} else {
    Write-Output "Request failed with status code $($response.StatusCode)"
    Write-Output "Error message:"
    $response.Content
}

# If you expect the response to be a JSON array
$responseData = $response | ConvertFrom-Json

# Check if the response is not empty
if ($responseData.Count -gt 0) {
    Write-Output "Received items:"
    $responseData | Format-Table
} else {
    Write-Output "No items found in the response."
}
