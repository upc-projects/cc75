//
//  ArticleCell.swift
//  iCatchUp
//
//  Created by Developer User on 10/31/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import UIKit

class ArticleCell: UICollectionViewCell {
    @IBOutlet weak var pictureImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    
    func update(from article: Article) {
        if let urlToImage = article.urlToImage {
            pictureImageView.setImageFrom(urlString: urlToImage, withDefaultNamed: "no-image", withErrorNamed: "no-image")
        }
        titleLabel.text = article.title
    }
    
}
