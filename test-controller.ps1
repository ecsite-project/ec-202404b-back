
# Set the URL of the Spring Boot application
\ = 'http://localhost:8080/api/getItemList'

# Send a GET request to the endpoint
try {
    \ = Invoke-WebRequest -Uri \ -Method Get -ErrorAction Stop

    # Check the status code
    if (\.StatusCode -eq 200) {
        Write-Output 'Request succeeded with status code 200'
        Write-Output 'Response body:'
        \.Content | ConvertFrom-Json | Format-Table
    } else {
        Write-Output 'Request failed with status code \'
        Write-Output 'Error message:'
        \.Content
    }
} catch {
    Write-Output 'Request failed: \'
}

# If you expect the response to be a JSON array
try {
    \ = \.Content | ConvertFrom-Json

    # Check if the response is not empty
    if (\.Count -gt 0) {
        Write-Output 'Received items:'
        \ | Format-Table
    } else {
        Write-Output 'No items found in the response.'
    }
} catch {
    Write-Output 'Failed to parse JSON response: \'
}

