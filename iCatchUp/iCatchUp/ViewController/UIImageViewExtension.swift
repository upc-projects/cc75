//
//  UIImageViewExtension.swift
//  iCatchUp
//
//  Created by Developer User on 10/18/18.
//  Copyright © 2018 UPC. All rights reserved.
//

import Foundation
import UIKit
import os

extension UIImageView {
    func setImage(fromAsset name: String) {
        DispatchQueue.main.async {
            self.image = UIImage(named: name)
        }
    }
    
    func setImageFrom(urlString: String,
                      withDefaultNamed defaultName: String?,
                      withErrorNamed errorName: String?) {
        guard let url = URL(string: urlString) else {
            let message = "Error while creating URL, default image assigned"
            os_log("%@", message)
            DispatchQueue.main.async {
                if let name = defaultName {
                    self.image = UIImage(named: name)
                }
            }
            return
        }
        URLSession.shared.dataTask(with: url) { (data, response, error) in
            guard let urlResponse = response as? HTTPURLResponse,
            urlResponse.statusCode == 200,
            let mimeType = response?.mimeType,
            mimeType.hasPrefix("image"),
            let data = data,
            error == nil,
            let image = UIImage(data: data) else {
                let message = "Error while requesting image, error image assigned"
                os_log("%@", message)
                DispatchQueue.main.async {
                    if let name = errorName {
                        self.image = UIImage(named: name)
                    }
                }
                return
            }
            DispatchQueue.main.async {
                self.image = image
                //let message = "Successful request, response image assigned"
                //os_log("%@", message)
            }
        }.resume()
    }
}

