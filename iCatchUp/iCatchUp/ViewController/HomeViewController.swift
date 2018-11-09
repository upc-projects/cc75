//
//  HomeViewController.swift
//  iCatchUp
//
//  Created by Developer User on 10/31/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import UIKit
import os

private let reuseIdentifier = "Cell"

class HomeViewController: UICollectionViewController {
    var articles: [Article] = [Article]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        NewsApi.getTopHeadlines(
            apiKey: "fecf4feeffa64e4da682e7d268612ce5",
            responseHandler: handleResponse,
            errorHandler: handleError)
    }
    
    private func handleResponse(response: ArticlesResponse) {
        if response.status == "error" {
            if let message = response.message {
                os_log("%@", message)
            }
            return
        }
        if let articles = response.articles {
            self.articles = articles
            self.collectionView.reloadData()
        }
    }
    
    private func handleError(error: Error) {
        let message = "\(error.localizedDescription)"
        os_log("%@", message)
    }
    
    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return articles.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! ArticleCell
    
        // Configure the cell
        cell.update(from: articles[indexPath.row])
        return cell
    }

}
